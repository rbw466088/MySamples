/**
 * Created By Liu Yuhong - 2017年11月12日
 */
package org.lyh.camel.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月12日
 */
//@Component
public class SimpleProducer {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Scheduled(cron = "*/5 * * * * *")
    public void createMessages() {
        System.out.println("sending message...");
        producerTemplate.sendBody("activemq:invoices", "Hello World");
    }

}