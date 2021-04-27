///**
// * Created By Liu Yuhong - 2017年10月26日
// */
//package org.lyh.camel.config;
//
//import org.apache.camel.RoutesBuilder;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**<pre>
// * Auto-Detecting Camel Routes:
// * Or creating a new route RouteBuilder bean in your @Configuration class:
// * </pre>
// * @author Liu, Yuhong
// * @version 1.0
// * @since 2017年10月26日
// */
//@Configuration
//public class MyRouterConfiguration {
//
//    @Bean
//    RoutesBuilder myRouter() {
//        return new RouteBuilder() {
//
//            @Override
//            public void configure() throws Exception {
//                from("jms:invoices").to("file:/invoices");
//            }
//        };
//    }
//}