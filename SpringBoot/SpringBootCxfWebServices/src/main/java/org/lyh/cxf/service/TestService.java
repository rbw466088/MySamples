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
@WebService(targetNamespace = "http://service.cxf.lyh.org/", name = "Test")
public interface TestService {

    String hello(String name);
}
