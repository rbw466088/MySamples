/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月8日
 */
package com.dxc.plm.linglong.test.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TestServiceRequest")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TestServiceRequest {

    enum Type{
        TYPE1, TYPE2, TYPE3
    }

	@XmlElement(name = "ID")
	public String id;

	@XmlElement(name = "NAME")
	public String name;

    @XmlElement(name = "Type")
    public Type type;

	@XmlElementWrapper(name="VALUES")
	@XmlElement(name = "VALUE")
	public List<String> values;

    @XmlElement(name = "WAITSECONDS")
    public Integer waitSeconds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

    public Integer getWaitSeconds() {
        return waitSeconds;
    }

    public void setWaitSeconds(Integer waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

}
