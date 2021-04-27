/**
 * Created By Liu Yuhong - 2017年10月12日
 */
package org.lyh.sender;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.lyh.sender.service.ScheduleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月12日
 */
@SpringBootApplication
public class Application {

    @Bean
    public Queue queue1() {
        return new ActiveMQQueue("aaa");
    }

    @Bean
    public Queue queue2() {
        return new ActiveMQQueue("bbb");
    }

    /**<pre>
     * 
     * </pre>
     * @param args
     */
    public static void main(String[] args) {
        ScheduleService.setIS_RUNNING(false);
        SpringApplication.run(Application.class, args);
    }

}
