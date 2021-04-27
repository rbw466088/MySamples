/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月8日
 */
package com.dxc.plm.linglong.test;

import java.net.URL;
import java.util.Calendar;

import com.dxc.plm.linglong.test.service.TestService;
import com.dxc.plm.linglong.test.service.TestServiceImplService;
import com.dxc.plm.linglong.test.service.TestServiceRequest;
import com.dxc.plm.linglong.test.service.TestServiceRequest.VALUES;
import com.dxc.plm.linglong.test.service.TestServiceResult;
import com.dxc.plm.linglong.test.service.Type;
import com.dxc.plm.linglong.test.util.HttpURLConnectionUtil;

public class WebServiceTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //        testSimpleServiceByAxis();
        //        testTestServiceByAxis();
        testTestServiceByHttp(args);
        //        testTestServiceByClient(args);
        //        testTestServiceByClient2();
    }

    //    /**
    //     * Test simpleService by Axis client API
    //     * 
    //     * @return
    //     */
    //    public static String testSimpleServiceByAxis() {
    //        System.out.println(" -> testSimpleServiceByAxis");
    //
    //        try {
    //            String endpointURL = "http://127.0.0.1:7001/test/TestService";
    //            String userName = "bbb";
    //
    //            Service service = new Service();
    //            Call call = (Call) service.createCall();
    //
    //            call.setTargetEndpointAddress(new URL(endpointURL));
    //            call.setOperationName(new QName("http://service.test.linglong.plm.dxc.com/", "SimpleTestService"));
    //            call.addParameter("name", XMLType.XSD_STRING, ParameterMode.IN);
    //
    //            String ret = (String) call.invoke(new Object[] { userName });
    //            System.out.println(" -> String Returned: " + ret);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //        return null;
    //    }

    //    /**
    //     * Test testService by Axis client API
    //     * 
    //     * @return
    //     */
    //    public static String testTestServiceByAxis() {
    //        System.out.println(" -> testSimpleServiceByAxis");
    //
    //        try {
    //            String endpointURL = "http://127.0.0.1:7001/test/TestService";
    //
    //            TestServiceRequest request = new TestServiceRequest();
    //            request.setID("111");
    //            request.setNAME("aaa");
    //
    //            Service service = new Service();
    //            Call call = (Call) service.createCall();
    //            QName qn = new QName("http://127.0.0.1:7001/test/TestService", "TestService");
    //            call.registerTypeMapping(TestServiceRequest.class, qn, new org.apache.axis.encoding.ser.BeanSerializerFactory(TestServiceRequest.class, qn),
    //                    new org.apache.axis.encoding.ser.BeanDeserializerFactory(TestServiceRequest.class, qn));
    //
    //            call.setTargetEndpointAddress(new java.net.URL(endpointURL));
    //            call.setOperationName(qn);
    //            call.addParameter("arg1", qn, ParameterMode.IN);
    //            call.setReturnClass(TestServiceResult.class);
    //
    //            TestServiceResult result = (TestServiceResult) call.invoke(new Object[] { request });
    //
    //            System.out.println(" -> Success...");
    //            System.out.println(result.getMESSAGE());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //        return null;
    //    }

    /**
     * Test testService by HTTP connection
     * 
     */
    private static void testTestServiceByHttp(String[] args) {
        System.out.println("");
        System.out.println(" -> testServiceByHttp, startTime: " + Calendar.getInstance().getTime());
        System.out.println("");

        String wsUrl = "http://127.0.0.1:7001/test";
        Integer waitSeconds = 30;
        if (args != null && args.length == 1) {
            wsUrl = args[0];
        } else if (args != null && args.length == 2) {
            wsUrl = args[0];
            waitSeconds = Integer.valueOf(args[1]);
        }

        StringBuffer request = new StringBuffer();
        request.append(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.test.linglong.plm.dxc.com/\">");
        request.append(" <soapenv:Header/>");
        request.append("<soapenv:Body>");
        request.append("<ser:TestService>");
        request.append("<TestServiceRequest>");

        request.append("<ID>111</ID>");
        request.append("<NAME>aaa</NAME>");
        request.append("<VALUES>");
        request.append("<VALUE>0001</VALUE>");
        request.append("<VALUE>0002</VALUE>");
        request.append("</VALUES>");
        request.append("<WAITSECONDS>" + waitSeconds + "</WAITSECONDS>");

        request.append("</TestServiceRequest>");
        request.append(" </ser:TestService>");
        request.append("</soapenv:Body>");
        request.append("</soapenv:Envelope>");
        try {
            HttpURLConnectionUtil.postRequest(wsUrl, request.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test testService by generated client codes
     */
    private static void testTestServiceByClient(String[] args) {
        System.out.println(" -> testTestServiceByClient");

        String wsUrl = "http://127.0.0.1:7001/test/TestService?wsdl";
        if (args != null && args.length > 0) {
            wsUrl = args[0];
        }
        TestServiceRequest request = new TestServiceRequest();
        request.setID("111");
        request.setNAME("aaa");
        VALUES values = new VALUES();
        values.getVALUE().add("0001");
        values.getVALUE().add("0002");
        request.setVALUES(values);
        try {
            URL url = new URL(wsUrl);
            TestServiceImplService service = new TestServiceImplService(url);
            TestService port = service.getTestServicePort();
            TestServiceResult result = port.testService(request);
            System.out.println(result.getMESSAGE());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test testService by generated client codes
     */
    private static void testTestServiceByClient2() {
        System.out.println(" -> testTestServiceByClient2");
        TestServiceRequest request = new TestServiceRequest();
        request.setType(Type.TYPE_1);
        request.setID("111");
        request.setNAME("aaa");
        VALUES values = new VALUES();
        values.getVALUE().add("0001");
        values.getVALUE().add("0002");
        request.setVALUES(values);
        try {
            URL url = new URL("http://127.0.0.1:7001/test/TestService?wsdl");
            TestServiceImplService service = new TestServiceImplService(url);
            TestService port = service.getTestServicePort();
            TestServiceResult result = port.testService(request);
            System.out.println(result.getMESSAGE());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
