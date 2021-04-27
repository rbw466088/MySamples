/**
 * Created By Liu Yuhong - 2017年11月29日
 */
package org.lyh.scheduler.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月29日
 */
@Service
@EnableScheduling
public class ScheduleService {

    private static Date date = Calendar.getInstance().getTime();

    @Scheduled(cron = "1/10 * * * * ?")
    public void test() {
        System.out.println("start date: " + date);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        date = Calendar.getInstance().getTime();
        System.out.println("end date: " + date);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
