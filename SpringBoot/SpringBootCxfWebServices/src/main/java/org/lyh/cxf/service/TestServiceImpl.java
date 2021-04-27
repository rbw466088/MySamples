/**
 * Created By Liu Yuhong - 2017年11月6日
 */
package org.lyh.cxf.service;

import javax.jws.WebService;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月6日
 */
@WebService(serviceName = "TestService", portName = "TestPort", targetNamespace = "http://service.cxf.lyh.org/", endpointInterface = "org.lyh.cxf.service.TestService")
public class TestServiceImpl implements TestService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
