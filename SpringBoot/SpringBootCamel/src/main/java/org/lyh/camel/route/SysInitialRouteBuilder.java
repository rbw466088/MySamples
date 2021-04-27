/**
 * Created By Liu Yuhong - 2017年11月4日
 */
package org.lyh.camel.route;

import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**<pre>
 * 系统路由初始化构建
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月4日
 */
@Component
public class SysInitialRouteBuilder extends SpringRouteBuilder {

    Logger logger = LoggerFactory.getLogger(SysInitialRouteBuilder.class);

    @Override
    public void configure() throws Exception {
        //        String from = "jetty:http://localhost:8080/route/add";
        //        logger.info("初始化创建路由1，from：{}，处理器：{}", from, processor.getClass().getName());
        //        from(from).routeId("route_sys_1").process(processor);

        //        restConfiguration().component("servlet").bindingMode(RestBindingMode.off);

        //        String from2 = "servlet:1";
        //        String to2 = "jms1:queue:UP.REQ.1?transacted=true&requestTimeout=60000&priority=6&replyTo=DOWN.ANS.1";
        //        to2 = "http:localhost:8088?bridgeEndpoint=true";
        //        logger.info("初始化创建路由2，from：{}，to：{}", from2, to2);
        //        from(from2).routeId("routeId_sys_2").to(to2);

        String from1 = "servlet:1";
        String to1 = "jms:queue:test_node_1?requestTimeout=60000";
        from(from1).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("节点1接收消息：" + exchange.getIn().getBody().toString());
            }
        }).to(to1);

        //
        //        String from2 = "activemq:test_node_1";
        //        String to2 = "activemq:test_node_2";
        //        from(from2).process(new Processor() {
        //            @Override
        //            public void process(Exchange exchange) throws Exception {
        //                System.out.println("节点2接收消息：" + exchange.getIn().getBody().toString());
        //            }
        //        }).to(to2);
        //
        //        String from3 = "activemq:test_node_2";
        //        String to3 = "http:127.0.0.1:7001/test/TestService?requestTimeout=10000&bridgeEndpoint=true";
        //        //        String to4 = "activemq:test_node_3";
        //        from(from3).process(new Processor() {
        //            @Override
        //            public void process(Exchange exchange) throws Exception {
        //                System.out.println("节点3接收消息：" + exchange.getIn().getBody().toString());
        //            }
        //        }).to(to3);

        //        String from4 = "activemq:test_node_3";
        //        String to5 = "activemq:test_node_1";
        //        from(from4).process(new Processor() {
        //            @Override
        //            public void process(Exchange exchange) throws Exception {
        //                
        //            }
        //        }).to(to5);

        String from2 = "jms:queue:test_node_1?concurrentConsumers=1&maxConcurrentConsumers=10&maxMessagesPerTask=100";
        //        String to2 = "bean:testService?method=test";
        String to2 = "http://127.0.0.1:7001/test?bridgeEndpoint=true";
        //        String to3 = "jms:queue:test_node_2";
        from(from2).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("======================== process time: " + Calendar.getInstance().getTime());
            }
        }).to(to2).onCompletion().process(new Processor() {

            @Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("------------------------ complete time: " + Calendar.getInstance().getTime());
            }
        });//.to(to3);

        //        String from3 = "servlet:1";
        //        String to3 = "http://localhost:8080/test?bridgeEndpoint=true";
        //        from(from3).process(new Processor() {
        //            @Override
        //            public void process(Exchange exchange) throws Exception {
        //                System.out.println("======================== process time: " + Calendar.getInstance().getTime());
        //            }
        //        }).to(to3);
    }
}