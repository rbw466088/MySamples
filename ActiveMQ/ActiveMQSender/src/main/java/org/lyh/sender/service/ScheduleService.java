/**
 * Created By Liu Yuhong - 2017年10月12日
 */
package org.lyh.sender.service;

import org.lyh.sender.common.ScheduleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月12日
 */
@Component
@EnableScheduling
@ConditionalOnProperty(name = "schedule.enabled")
public class ScheduleService {

    @Autowired
    SendService service;

    /**
     * 正在运行标志，true-在运行，false-没有运行
     */
    private static boolean IS_RUNNING;

    public synchronized static boolean isIS_RUNNING() {
        return IS_RUNNING;
    }

    public static void setIS_RUNNING(boolean iS_RUNNING) {
        IS_RUNNING = iS_RUNNING;
    }

    @Autowired
    ScheduleProperties properties;

    @Scheduled(cron = "${schedule.send}")
    // 每3s执行1次
    public void job1() {
        System.out.println("定时任务运行状态：" + isIS_RUNNING());
        if (isIS_RUNNING()) {
            System.out.println("定时任务正在运行，本次运行取消");
            return;
        } else {
            service.send1();
        }
    }

    //    @Scheduled(cron = "${schedule.send}")
    // 每3s执行1次
    public void job2() {
        System.out.println("定时任务运行状态：" + isIS_RUNNING());
        if (isIS_RUNNING()) {
            System.out.println("定时任务正在运行，本次运行取消");
            return;
        } else {
            service.send2();
        }
    }

}