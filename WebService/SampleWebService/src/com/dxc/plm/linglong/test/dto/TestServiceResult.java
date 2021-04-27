/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月8日
 */
package com.dxc.plm.linglong.test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TestServiceResult")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TestServiceResult {

	@XmlElement(name = "RESULT")
	public String result;

	@XmlElement(name = "MESSAGE")
	public String message;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
