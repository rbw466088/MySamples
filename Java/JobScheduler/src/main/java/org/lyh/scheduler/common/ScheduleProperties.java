/**
 * Created By Liu Yuhong - 2017年10月23日
 */
package org.lyh.scheduler.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月23日
 */
@PropertySource("classpath:config/schedule.properties")
@Component
public class ScheduleProperties {

    /**
     * 线程调度器开关，1-开启，0-关闭
     */
    @Value("${scheduler.scheduleEnable}")
    private boolean scheduleEnable;

    /**
     * 线程调度器等待时间，单位秒
     */
    @Value("${scheduler.waitTime}")
    private int schedulerWaitTime;

    /**
     * 线程调度器，线程池大小
     */
    @Value("${scheduler.poolSize}")
    private int schedulerPoolSize;

    /**
     * 任务过期时间，单位秒
     */
    @Value("${job.overTime}")
    private int jobOverTime;

    /**
     * 任务失败等待时间，单位秒
     */
    @Value("${job.failWaitTime}")
    private int jobFailWaitTime;

    public boolean isScheduleEnable() {
        return scheduleEnable;
    }

    public int getSchedulerWaitTime() {
        return schedulerWaitTime;
    }

    public int getSchedulerPoolSize() {
        return schedulerPoolSize;
    }

    public int getJobOverTime() {
        return jobOverTime;
    }

    public int getJobFailWaitTime() {
        return jobFailWaitTime;
    }

}
