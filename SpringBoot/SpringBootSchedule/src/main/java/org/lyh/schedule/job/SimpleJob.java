/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月26日<br />
 */
package org.lyh.schedule.job;

import org.lyh.schedule.service.SimpleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleJob implements Job{

    @Autowired
    private SimpleService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        service.processData();
    }
}