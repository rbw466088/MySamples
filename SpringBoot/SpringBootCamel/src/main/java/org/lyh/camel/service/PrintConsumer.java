/**
 * Created By Liu Yuhong - 2017年11月12日
 */
package org.lyh.camel.service;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月12日
 */
@Component("printConsumer")
public class PrintConsumer {

    @Handler
    public void printMessage(@Body Object message) {
        System.out.println("msg=" + message);
    }
}