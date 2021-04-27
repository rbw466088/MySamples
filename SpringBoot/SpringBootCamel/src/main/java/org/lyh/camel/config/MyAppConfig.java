///**
// * Created By Liu Yuhong - 2017年10月26日
// */
//package org.lyh.camel.config;
//
//import org.apache.camel.CamelContext;
//import org.apache.camel.spring.boot.CamelContextConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**<pre>
// * Auto-Configured Camel Context:
// * The most important piece of functionality provided by the Camel auto-configuration is CamelContext instance. 
// * Camel auto-configuration creates a SpringCamelContext for you and takes care of the proper initialization 
// * and shutdown of that context. The created Camel context is also registered in the Spring application context 
// * (under camelContext bean name), so you can access it just as  any other Spring bean.
// * </pre>
// * @author Liu, Yuhong
// * @version 1.0
// * @since 2017年10月26日
// */
//@Configuration
//public class MyAppConfig {
//
//    @Autowired
//    CamelContext camelContext;
//
//    //    @Bean
//    //    MyService myService() {
//    //        return new DefaultMyService(camelContext);
//    //    }
//
//    /**
//     * Custom Camel Context Configuration
//     */
//    @Bean
//    CamelContextConfiguration contextConfiguration() {
//        return new CamelContextConfiguration() {
//
//            /**
//             * Method CamelContextConfiguration#beforeApplicationStart(CamelContext) will be called just before 
//             * the Spring context is started, so the CamelContext instance passed to this callback is fully 
//             * auto-configured. You can add many instances of CamelContextConfiguration into your Spring context - 
//             * all of them will be executed.
//             */
//            @Override
//            public void beforeApplicationStart(CamelContext arg0) {
//                // TODO Auto-generated method stub
//
//            }
//
//            /**
//             * If you would like to perform some operations on CamelContext bean created by Camel auto-configuration, 
//             * register CamelContextConfiguration instance in your Spring context:
//             */
//            @Override
//            public void afterApplicationStart(CamelContext arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        };
//    }
//}