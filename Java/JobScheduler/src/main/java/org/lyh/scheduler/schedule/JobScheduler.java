/**
 * Created By Liu Yuhong - 2017年11月22日
 */
package org.lyh.scheduler.schedule;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**<pre>
 * 任务调度器：
 * 1，管理任务队列
 * 2，管理任务线程池
 * 3，协调任务执行状态
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月22日
 */
public class JobScheduler<BaseJob> {
    
    private static final Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    /**
     * 任务队列，包含正在执行和待执行的任务
     */
    private CopyOnWriteArrayList<BaseJob> jobQueue;

    /**
     * 线程任务列表，包含正在执行的任务
     */
    private CopyOnWriteArrayList<BaseJob> threadJobList;

    /**
     * 任务线程池
     */
    private ThreadPoolExecutor threadPool;


    public CopyOnWriteArrayList<BaseJob> getJobQueue() {
        if (jobQueue == null) {
            jobQueue = new CopyOnWriteArrayList<BaseJob>();
        }
        return jobQueue;
    }

    public CopyOnWriteArrayList<BaseJob> getThreadJobList() {
        if (threadJobList == null) {
            threadJobList = new CopyOnWriteArrayList<BaseJob>();
        }
        return threadJobList;
    }

    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    /**
     * 单例的任务调度器
     */
    @SuppressWarnings("rawtypes")
    private static JobScheduler jobScheduler;

    private JobScheduler() {}

    @SuppressWarnings("unchecked")
    public JobScheduler<BaseJob> getInstance() {
        if (jobScheduler == null) {
            jobScheduler = new JobScheduler<BaseJob>();
        }
        return jobScheduler;
    }

    /**
     * <pre>
     * 向任务队列添加任务
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月22日
     * @param job
     */
    public void addJob(BaseJob job) {
        
    }

    /**
     * <pre>
     * 从任务队列里移除任务
     * 注：在线程任务列表里，已经在执行的任务不可移除
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月22日
     * @param job
     */
    public void removeJob(BaseJob job) {
        
    }

    /**
     * <pre>
     * 检查任务队列里，是否包含此任务
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月22日
     * @param job
     * @return
     */
    public boolean hasJob(BaseJob job) {
        return false;
    }

    /**
     * <pre>
     * 执行
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月22日
     */
    public void executeJob() {
        
    }

}
