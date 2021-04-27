/**
 * Created By Liu Yuhong - 2017年10月26日
 */
package org.lyh.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**<pre>
 * 启动入口类
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月26日
 */
@SpringBootApplication
public class Application {

    private static final String CAMEL_URL_MAPPING = "/Camel/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    /**<pre>
     * 启动入口方法
     * </pre>
     * @param args
     */
    public static void main(String[] args) {
        final ApplicationContext context = new SpringApplication(Application.class).run(args);
        // Spring Boot启动时初始化Camel controller
        final CamelSpringBootApplicationController controller = context.getBean(CamelSpringBootApplicationController.class);
        controller.run();
    }

    /**
     * <pre>
     * For using Camel Servlet in spring boot
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月13日
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }

//    /**
//     * For using Camel annotations in spring beans.
//     *
//     * @param camelContext
//     * @param applicationContext
//     * @return
//     */
//    @Bean
//    public CamelBeanPostProcessor camelBeanPostProcessor(CamelContext camelContext, ApplicationContext applicationContext) {
//        CamelBeanPostProcessor processor = new CamelBeanPostProcessor();
//        processor.setCamelContext(camelContext);
//        processor.setApplicationContext(applicationContext);
//        return processor;
//    }
//
//    @Bean
//    public ProducerTemplate producerTemplate(CamelContext camelContext) {
//        return camelContext.createProducerTemplate();
//    }
//
//    @Bean
//    public ActiveMQConnectionFactory coreConnectionFactory() {
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        connectionFactory.setBrokerURL("vm://localhost?broker.persistent=false");
//        return connectionFactory;
//    }
//
//    @Bean
//    public JmsConfiguration jmsConfiguration(ActiveMQConnectionFactory coreConnectionFactory) {
//        JmsConfiguration jmsConfiguration = new JmsConfiguration();
//        ConnectionFactory connectionFactory = new PooledConnectionFactory(coreConnectionFactory);
//        jmsConfiguration.setConnectionFactory(connectionFactory);
//        return jmsConfiguration;
//    }
//
//    @Bean
//    public ActiveMQComponent activeMQComponent(JmsConfiguration jmsConfiguration) {
//        ActiveMQComponent component = new ActiveMQComponent();
//        component.setConfiguration(jmsConfiguration);
//        return component;
//    }
//
//    @Bean
//    public JmsComponent jmsComponent(JmsConfiguration jmsConfiguration) {
//        JmsComponent component = new JmsComponent();
//        component.setConfiguration(jmsConfiguration);
//        return component;
//    }

    @Bean
    public HttpComponent httpComponent(CamelContext context, HttpConnectionManager httpConnectionManager) {
        HttpComponent httpComponent = new HttpComponent();
        httpComponent.setHttpConnectionManager(httpConnectionManager);
        return httpComponent;
    }

    @Bean
    public MultiThreadedHttpConnectionManager httpConnectionManager(HttpConnectionManagerParams params) {
        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setParams(params);
        return manager;
    }

    @Bean
    public HttpConnectionManagerParams httpConnectionManagerParams() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setDefaultMaxConnectionsPerHost(100);
        params.setConnectionTimeout(60000);
        params.setMaxTotalConnections(100);
        return params;
    }

}
