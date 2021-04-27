/**
 * Created By Liu Yuhong - 2017年10月17日
 */
package org.lyh.sender.service;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月17日
 */
@Service
@EnableAsync
public class SendService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier("queue1")
    private Queue queue1;

    @Autowired
    @Qualifier("queue2")
    private Queue queue2;

    @Async //将要调用的方法标记为异步方法，将放到线程池里执行
    public void send1() {
        System.out.println("定时任务1，发送到MQ");
        ScheduleService.setIS_RUNNING(true);
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("send1: " + (i + 1));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean canSend = false;
        if (canSend) {
            this.jmsMessagingTemplate.convertAndSend(this.queue2, "hi,activeMQ");
        }

        ScheduleService.setIS_RUNNING(false);
        System.out.println("定时任务1完成运行");

    }

    @Async
    public void send2() {
        System.out.println("定时任务2，发送到MQ");
        ScheduleService.setIS_RUNNING(true);
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("send2: " + (i + 1));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean canSend = false;
        if (canSend) {
            this.jmsMessagingTemplate.convertAndSend(this.queue2, "hi,activeMQ");
        }

        ScheduleService.setIS_RUNNING(false);
        System.out.println("定时任务2完成运行");
    }

}
