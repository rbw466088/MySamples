
package com.dxc.plm.linglong.test.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dxc.plm.linglong.test.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestService_QNAME = new QName("http://service.test.linglong.plm.dxc.com/", "TestService");
    private final static QName _TestServiceResponse_QNAME = new QName("http://service.test.linglong.plm.dxc.com/", "TestServiceResponse");
    private final static QName _TestServiceRequest_QNAME = new QName("http://service.test.linglong.plm.dxc.com/", "TestServiceRequest");
    private final static QName _SimpleTestServiceResponse_QNAME = new QName("http://service.test.linglong.plm.dxc.com/", "SimpleTestServiceResponse");
    private final static QName _SimpleTestService_QNAME = new QName("http://service.test.linglong.plm.dxc.com/", "SimpleTestService");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dxc.plm.linglong.test.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestServiceRequest }
     * 
     */
    public TestServiceRequest createTestServiceRequest() {
        return new TestServiceRequest();
    }

    /**
     * Create an instance of {@link TestService_Type }
     * 
     */
    public TestService_Type createTestService_Type() {
        return new TestService_Type();
    }

    /**
     * Create an instance of {@link TestServiceResponse }
     * 
     */
    public TestServiceResponse createTestServiceResponse() {
        return new TestServiceResponse();
    }

    /**
     * Create an instance of {@link SimpleTestServiceResponse }
     * 
     */
    public SimpleTestServiceResponse createSimpleTestServiceResponse() {
        return new SimpleTestServiceResponse();
    }

    /**
     * Create an instance of {@link SimpleTestService }
     * 
     */
    public SimpleTestService createSimpleTestService() {
        return new SimpleTestService();
    }

    /**
     * Create an instance of {@link TestServiceResult }
     * 
     */
    public TestServiceResult createTestServiceResult() {
        return new TestServiceResult();
    }

    /**
     * Create an instance of {@link TestServiceRequest.VALUES }
     * 
     */
    public TestServiceRequest.VALUES createTestServiceRequestVALUES() {
        return new TestServiceRequest.VALUES();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestService_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.test.linglong.plm.dxc.com/", name = "TestService")
    public JAXBElement<TestService_Type> createTestService(TestService_Type value) {
        return new JAXBElement<TestService_Type>(_TestService_QNAME, TestService_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.test.linglong.plm.dxc.com/", name = "TestServiceResponse")
    public JAXBElement<TestServiceResponse> createTestServiceResponse(TestServiceResponse value) {
        return new JAXBElement<TestServiceResponse>(_TestServiceResponse_QNAME, TestServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestServiceRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.test.linglong.plm.dxc.com/", name = "TestServiceRequest")
    public JAXBElement<TestServiceRequest> createTestServiceRequest(TestServiceRequest value) {
        return new JAXBElement<TestServiceRequest>(_TestServiceRequest_QNAME, TestServiceRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleTestServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.test.linglong.plm.dxc.com/", name = "SimpleTestServiceResponse")
    public JAXBElement<SimpleTestServiceResponse> createSimpleTestServiceResponse(SimpleTestServiceResponse value) {
        return new JAXBElement<SimpleTestServiceResponse>(_SimpleTestServiceResponse_QNAME, SimpleTestServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleTestService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.test.linglong.plm.dxc.com/", name = "SimpleTestService")
    public JAXBElement<SimpleTestService> createSimpleTestService(SimpleTestService value) {
        return new JAXBElement<SimpleTestService>(_SimpleTestService_QNAME, SimpleTestService.class, null, value);
    }

}
