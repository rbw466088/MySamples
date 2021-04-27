/**
 * Created By Liu Yuhong - 2017年11月13日
 */
package org.lyh.camel.web;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月13日
 */
@Controller
public class RouteController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("接收到请求，时间：" + Calendar.getInstance().getTime());
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("返回应答，时间：" + Calendar.getInstance().getTime());
        return "Route is running. ";
    }
}
