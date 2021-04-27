/**
 * Created By Liu Yuhong - 2017年10月12日
 */
package org.lyh.receiver.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月12日
 */
@Component
public class ReceiverService {

    @JmsListener(destination = "aaa")
    public void receiveQueue1(String text) {
        System.out.println("接收到aaa消息：" + text);
    }

    @JmsListener(destination = "bbb")
    public void receiveQueue2(String text) {
        System.out.println("接收到bbb消息：" + text);
    }
}