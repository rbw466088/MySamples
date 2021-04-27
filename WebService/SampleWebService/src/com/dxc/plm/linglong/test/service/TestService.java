/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月8日
 */
package com.dxc.plm.linglong.test.service;

import com.dxc.plm.linglong.test.dto.TestServiceRequest;
import com.dxc.plm.linglong.test.dto.TestServiceResult;

public interface TestService {

	/**
	 * Simple service with String parameter
	 * 
	 * @param name
	 * @return
	 */
	public String simpleTestService(String name);

	/**
	 * Standard service with Object parameter
	 * 
	 * @param request
	 * @return
	 */
	public TestServiceResult testService(TestServiceRequest request);

}
