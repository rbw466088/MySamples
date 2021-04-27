/**
 * Created By Liu Yuhong - 2017年10月23日
 */
package org.lyh.scheduler.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lyh.scheduler.service.BaseJobService;
import org.lyh.scheduler.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月23日
 */
public abstract class BaseJob implements Runnable {

    Logger logger = LoggerFactory.getLogger(BaseJob.class);

    /**
     * 异常处理service
     */
    private BaseJobService service;

    /**
     * 线程分组id
     */
    private String groupId;

    /**
     * 任务处理的记录列表
     */
    private CopyOnWriteArrayList<BaseRecord> jobRecordList;

    /**
     * 任务启动的时间
     */
    private Date jobStartDate;

    /**
     * 任务有效期（秒）
     */
    private int threadOverTime;

    /**
     * 任务结束标志
     */
    private boolean isFinish;

    /**
     * 任务权重
     */
    private int jobWeight;

    /**
     * 任务失败时间
     */
    private Date failTime;

    /**
     * 任务失败的等待时间
     */
    private int failWaitTime;

    protected BaseJob(BaseJobService service, String groupId, CopyOnWriteArrayList<BaseRecord> jobRecordList, Integer threadOverTime,  int failWaitTime) {
        this.service = service;
        this.groupId = groupId;
        this.jobRecordList = jobRecordList;
        this.threadOverTime = threadOverTime;
        this.failWaitTime = failWaitTime;
        isFinish = false;
        jobWeight = 0;
    }

    @Override
    public void run() {
        jobStartDate = Calendar.getInstance().getTime();
        logger.info("任务处理，启动时间：{}，分组id：{}，启动时任务记录数：{}", jobStartDate, groupId, jobRecordList.size());

        while (!isFinish) {
            List<BaseRecord> removeRecordList = new ArrayList<BaseRecord>();
            for (int i = 0; i < jobRecordList.size(); i++) {
                BaseRecord jobRecord = jobRecordList.get(i);
                if (DateTimeUtil.isDateTimeExpired(jobStartDate, threadOverTime)) {
                    logger.info("任务过期，分组id：{}，剩余任务记录数：{}", groupId, jobRecordList.size());
                    break;
                }

                if (callService(jobRecord)) {
                    logger.info("任务处理，调用业务接口成功，分组id：{}", groupId);
                } else {
                    logger.info("任务处理，调用业务接口失败，分组id：{}，等待{}秒重试", groupId, failWaitTime);
                    failTime = Calendar.getInstance().getTime();
                    break;
                }
            }

            jobRecordList.removeAll(removeRecordList);
            isFinish = true;
        }

        logger.info("任务处理 结束");
    }

    /**
     * <pre>
     * 调用业务接口
     * </pre>
     * @param jobRecord
     * @return true-成功，false-失败
     */
    protected abstract Boolean callService(BaseRecord jobRecord);

    public BaseJobService getService() {
        return service;
    }

    public String getGroupId() {
        return groupId;
    }

    public CopyOnWriteArrayList<BaseRecord> getJobRecordList() {
        return jobRecordList;
    }

    public Date getJobStartDate() {
        return jobStartDate;
    }

    public int getThreadOverTime() {
        return threadOverTime;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public int getJobWeight() {
        return jobWeight;
    }

    public void setJobWeight(int jobWeight) {
        this.jobWeight = jobWeight;
    }

    public Date getFailTime() {
        return failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

    public int getFailWaitTime() {
        return failWaitTime;
    }

}
