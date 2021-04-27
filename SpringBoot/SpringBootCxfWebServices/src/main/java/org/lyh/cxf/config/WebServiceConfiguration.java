/**
 * Created By Liu Yuhong - 2017年11月6日
 */
package org.lyh.cxf.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.lyh.cxf.service.TestService;
import org.lyh.cxf.service.TestServiceImpl;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月6日
 */
@Configuration
public class WebServiceConfiguration {

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap-sample/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public TestService testService() {
        return new TestServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), testService());

        /**
         * CXF JAX-WS implementation relies on the correct ServiceName as QName-Object with
         * the name-Attribute´s text <wsdl:service name="Weather"> and the targetNamespace
         * "http://www.codecentric.de/namespace/weatherservice/"
         * Also the WSDLLocation must be set
         */
        //        endpoint.setServiceName(weather().getServiceName());
        //        endpoint.setWsdlLocation(testService().getWSDLDocumentLocation().toString());

        endpoint.publish("/testService");
        return endpoint;
    }

}
