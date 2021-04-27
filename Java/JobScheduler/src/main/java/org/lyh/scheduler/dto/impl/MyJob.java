/**
 * Created By Liu Yuhong - 2017年10月23日
 */
package org.lyh.scheduler.dto.impl;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lyh.scheduler.dto.BaseJob;
import org.lyh.scheduler.dto.BaseRecord;
import org.lyh.scheduler.service.BaseJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月23日
 */
public class MyJob extends BaseJob {

    Logger logger = LoggerFactory.getLogger(MyJob.class);

    public MyJob(BaseJobService service, String groupId, CopyOnWriteArrayList<BaseRecord> jobRecordList, Integer threadOverTime, int failWaitTime) {
        super(service, groupId, jobRecordList, threadOverTime, failWaitTime);
    }

    /**
     * <pre>
     * 调用Service
     * </pre>
     * @param jobRecord
     * @return true-成功，false-失败
     */
    @Override
    public Boolean callService(BaseRecord jobRecord) {
        logger.info("调用业务接口 开始");

        getService().executeService(jobRecord);
        logger.info("调用业务接口 成功");
        return true;
    }
}
