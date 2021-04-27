
package com.dxc.plm.linglong.test.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TestServiceResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TestServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestServiceResult" type="{http://service.test.linglong.plm.dxc.com/}TestServiceResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestServiceResponse", propOrder = {
    "testServiceResult"
})
public class TestServiceResponse {

    @XmlElement(name = "TestServiceResult")
    protected TestServiceResult testServiceResult;

    /**
     * 获取testServiceResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TestServiceResult }
     *     
     */
    public TestServiceResult getTestServiceResult() {
        return testServiceResult;
    }

    /**
     * 设置testServiceResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TestServiceResult }
     *     
     */
    public void setTestServiceResult(TestServiceResult value) {
        this.testServiceResult = value;
    }

}
