/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月18日<br />
 */
package org.lyh.drools.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.util.Strings;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.lyh.drools.domain.AlertDecision;
import org.lyh.drools.domain.Event;
import org.lyh.drools.domain.Message;
import org.lyh.drools.domain.OrderEvent;
import org.lyh.drools.rule.Condition;
import org.lyh.drools.rule.Rule;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class DroolsService {

    public String runByRule() {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        // go !
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kSession.insert(message);//插入
        kSession.fireAllRules();//执行规则
        kSession.dispose();
        return message.getMessage();
    }

    public String runBySimpleTemplate() throws Exception {
        Collection<Map<String, Object>> ms = new ArrayList<Map<String, Object>>();

        // 1, build fact object
        Message message = new Message();
        message.setStatus(Message.HELLO);
        message.setMessage("Hello World");

        // 2, compile drl
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("packageId", "org.lyh.drools");
        m.put("ruleId", "rule_1");
        m.put("factType", Message.class.getName());
        m.put("whenStatement", "(status == 0, myMessage : message)");
        m.put("thenStatement", "System.out.println(myMessage);");
        ms.add(m);

        ObjectDataCompiler converter = new ObjectDataCompiler();
        InputStream templateStream = this.getClass().getResourceAsStream("/template/template2.drl");
        String drl = converter.compile(ms, templateStream);

        System.out.println("--------------------------------");
        System.out.println(drl);
        System.out.println("--------------------------------");

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rules/rule.drl", drl);
        kieServices.newKieBuilder(kieFileSystem).buildAll();

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        StatelessKieSession statelessKieSession = kieContainer.getKieBase().newStatelessKieSession();

        statelessKieSession.execute(message);

        return message.getMessage();
    }

    public String runByComplexTemplate(String price, String name) throws Exception {

        // build fact object
        OrderEvent orderEvent = new OrderEvent();
        if (Strings.isNullOrEmpty(price) || Strings.isNullOrEmpty(name)) {
            orderEvent.setPrice(Double.valueOf(price));
            orderEvent.setCustomer(name);
        } else {
            orderEvent.setPrice(5000.1);
            orderEvent.setCustomer("Liuyuhong");
        }

        // build rule
        Rule highValueOrderWidgetsIncRule = new Rule();

        Condition highValueOrderCondition = new Condition();
        highValueOrderCondition.setField("price");
        highValueOrderCondition.setOperator(Condition.Operator.GREATER_THAN);
        highValueOrderCondition.setValue(5000.0);

        Condition widgetsIncCustomerCondition = new Condition();
        widgetsIncCustomerCondition.setField("customer");
        widgetsIncCustomerCondition.setOperator(Condition.Operator.EQUAL_TO);
        widgetsIncCustomerCondition.setValue("Liuyuhong");

        // In reality, you would have multiple rules for different types of events.
        // The eventType property would be used to find rules relevant to the event
        highValueOrderWidgetsIncRule.setEventType(Rule.eventType.ORDER);
        highValueOrderWidgetsIncRule.setConditions(Arrays.asList(highValueOrderCondition, widgetsIncCustomerCondition));

        String drl = applyRuleTemplate(orderEvent, highValueOrderWidgetsIncRule);

        System.out.println("--------------------------------");
        System.out.println(drl);
        System.out.println("--------------------------------");

        AlertDecision alertDecision = evaluate(drl, orderEvent);

        System.out.println(alertDecision.getDoAlert());

        // doAlert is false by default
        if (alertDecision.getDoAlert()) {
            // do notification
            return "Alert !!!";
        }

        return "Nothing !!!";
    }

    static private AlertDecision evaluate(String drl, Event event) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rules/rule.drl", drl);
        kieServices.newKieBuilder(kieFileSystem).buildAll();

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        StatelessKieSession statelessKieSession = kieContainer.getKieBase().newStatelessKieSession();

        AlertDecision alertDecision = new AlertDecision();
        statelessKieSession.getGlobals().set("alertDecision", alertDecision);
        statelessKieSession.execute(event);

        return alertDecision;
    }

    static private String applyRuleTemplate(Event event, Rule rule) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();

        data.put("rule", rule);
        data.put("eventType", event.getClass().getName());

        return objectDataCompiler.compile(Arrays.asList(data), Thread.currentThread().getContextClassLoader().getResourceAsStream("rule-template.drl"));
    }

    public void DroolsTemplateTest1() throws IOException {
        Collection<Message> ms = new ArrayList<Message>();

        // 1, build fact object
        Message m = new Message();
        m.setStatus(Message.HELLO);
        m.setMessage("Hello World");
        ms.add(m);

        // 2, compile drl
        ObjectDataCompiler converter = new ObjectDataCompiler();
        InputStream templateStream = this.getClass().getResourceAsStream("/template/template2.drl");
        String drl = converter.compile(ms, templateStream);

        // 3, load up the knowledge base and add drl
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write("src/main/resources/simple.drl", drl);
        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();

        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

        // 4, use new KieContainer instead of KieClasspathContainer
        // KieContainer kContainer = ks.getKieClasspathContainer();
        // KieSession kSession = kContainer.newKieSession("ksession-rules");

        KieContainer kieContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
        KieSession kSession = kieContainer.newKieSession();

        // insert test data and run rules
        kSession.insert(m);
        kSession.fireAllRules();
    }

    public void DroolsTemplateTest2() throws IOException {
        Collection<Map<String, Object>> ms = new ArrayList<Map<String, Object>>();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("ruleId", "rule_1");
        m.put("factType", Message.class.getName());
        m.put("whenStatement", "(status == 0, myMessage : message)");
        m.put("thenStatement", "System.out.println(myMessage);");
        ms.add(m);

        // 2, compile drl
        ObjectDataCompiler converter = new ObjectDataCompiler();
        InputStream templateStream = this.getClass().getResourceAsStream("/template/template2.drl");
        String drl = converter.compile(ms, templateStream);
        System.out.println(drl);

        // 3, load up the knowledge base and add drl
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write("src/main/resources/simple.drl", drl);
        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();

        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

        // 4, use new KieContainer instead of KieClasspathContainer
        // KieContainer kContainer = ks.getKieClasspathContainer();
        // KieSession kSession = kContainer.newKieSession("ksession-rules");

        KieContainer kieContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
        KieSession kSession = kieContainer.newKieSession();

        // insert test data and run rules
        Message mes = new Message();
        mes.setStatus(Message.HELLO);
        mes.setMessage("222");
        kSession.insert(mes);
        kSession.fireAllRules();
    }
}