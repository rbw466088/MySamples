/**
 * Created By Liu Yuhong - 2017年11月17日
 */
package org.lyh.camel.service;

import java.util.Calendar;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.lyh.camel.util.HttpURLConnectionUtil;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月17日
 */
@Service
public class TestService implements Runnable {

    public String test(final Exchange exchange, final Message in, CamelContext context) {
        System.out.println("【接收到请求】，时间：" + Calendar.getInstance().getTime());
        System.out.println("收到消息：" + new String((byte[])in.getBody()));

        testTestServiceByHttp(null);

        System.out.println("请求完成，时间：" + Calendar.getInstance().getTime());
        return "Test OK !!!";
    }

    public static void main(String[] args) {
        
        ExecutorThreadPool exector = new ExecutorThreadPool(5);
        TestService service1 = new TestService();
        TestService service2 = new TestService();
        TestService service3 = new TestService();
        TestService service4 = new TestService();
        TestService service5 = new TestService();
        exector.execute(service1);
        exector.execute(service2);
        exector.execute(service3);
        exector.execute(service4);
        exector.execute(service5);
    }

    private static void testTestServiceByHttp(String[] args) {
        String wsUrl = "http://192.168.139.130:7001/test";
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

    @Override
    public void run() {
        testTestServiceByHttp(null);
    }
}
