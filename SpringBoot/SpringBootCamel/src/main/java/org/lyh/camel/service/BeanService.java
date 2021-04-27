/**
 * Created By Liu Yuhong - 2017年11月4日
 */
package org.lyh.camel.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**<pre>
 * 路由服务接口
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月4日
 */
@Component
public class BeanService implements BaseService {

    Logger logger = LoggerFactory.getLogger(BeanService.class);

    public static void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("beanService", new BeanService());

        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct://beanService").to("bean:beanService?method=test1(aaaa)").to("bean:beanService?method=test2(${body})");
            }
        });

        /**
         * 使用ProducerTemplate来调用路由
         */
        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
        template.sendBody("direct:beanService", "This is bean example");

        /**
         * 使用接口创建proxy来调用方法
         */
        //        Endpoint endpoint = camelContext.getEndpoint("direct:beanService");
        //        BaseService proxy = ProxyHelper.createProxy(endpoint, BaseService.class);
        //        String message = proxy.test("");
        //        System.out.println(message);

        camelContext.stop();
    }

    @Override
    public String test1(String msg) {
        String message = "Camel Bean test1 !!!!!!!!!!!!!!!!!!!!";
        System.out.println("Call Bean Method test1 and msg: " + msg);
        return message;
    }

    @Override
    public String test2(String msg) {
        String message = "Camel Bean test2 !!!!!!!!!!!!!!!!!!!!";
        System.out.println("Call Bean Method test2 and msg=" + msg);
        return message;
    }
}
