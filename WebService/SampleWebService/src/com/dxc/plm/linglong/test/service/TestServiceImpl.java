/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月8日
 */
package com.dxc.plm.linglong.test.service;

import java.util.Calendar;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.dxc.plm.linglong.test.dto.TestServiceRequest;
import com.dxc.plm.linglong.test.dto.TestServiceResult;

@WebService(targetNamespace = "http://service.test.linglong.plm.dxc.com/", name = "TestService")
public class TestServiceImpl implements TestService {

    private static String result = "";

	public static void setResult(String result) {
        TestServiceImpl.result = result;
    }

    @Override
    @WebMethod(operationName = "TestService")
	public @WebResult(name = "TestServiceResult") TestServiceResult testService(@WebParam(name = "TestServiceRequest") TestServiceRequest request) {
        System.out.println("服务请求开始时间，" + Calendar.getInstance().getTime());
        Integer waitSeconds = request.getWaitSeconds();
        try {
            Thread.sleep(waitSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Processing testService request ------------------------------------------------");
		System.out.println("ID :" + request.getId());
		System.out.println("NAME:" + request.getName());
        System.out.println("Type: " + request.getType());
		System.out.println("VALUES: " + request.getValues());
        System.out.println("Wait Seconds: " + waitSeconds);
		TestServiceResult result = new TestServiceResult();
		result.setResult("S");
		result.setMessage(TestServiceImpl.result);

        System.out.println("服务请求结束时间，" + Calendar.getInstance().getTime());
		return result;
	}

	@Override
    @WebMethod(operationName = "SimpleTestService")
	public @WebResult(name = "SimpleTestServiceResult") String simpleTestService(@WebParam(name = "name") String name) {
		System.out.println("Processing simpleTestService request ------------------------------------------------");
		System.out.println("name: " + name);
		return "Hello " + name;
	}
}
