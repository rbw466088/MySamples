/**
 * Created By Liu Yuhong - 2017年11月12日
 */
package org.lyh.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月12日
 */
//@Component
public class MyCamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("servlet:1").to("bean:printConsumer", "mock:printConsumer");
    }
}