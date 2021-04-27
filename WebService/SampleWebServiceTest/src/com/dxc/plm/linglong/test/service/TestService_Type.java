
package com.dxc.plm.linglong.test.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TestService complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TestService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestServiceRequest" type="{http://service.test.linglong.plm.dxc.com/}testServiceRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestService", propOrder = {
    "testServiceRequest"
})
public class TestService_Type {

    @XmlElement(name = "TestServiceRequest")
    protected TestServiceRequest testServiceRequest;

    /**
     * 获取testServiceRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TestServiceRequest }
     *     
     */
    public TestServiceRequest getTestServiceRequest() {
        return testServiceRequest;
    }

    /**
     * 设置testServiceRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TestServiceRequest }
     *     
     */
    public void setTestServiceRequest(TestServiceRequest value) {
        this.testServiceRequest = value;
    }

}
