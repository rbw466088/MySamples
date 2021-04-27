/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月25日<br />
 */
package org.lyh.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 */
@Controller
@RequestMapping(value = "/")
public class HelloWorldController {

    @RequestMapping(value = "/Hello", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "Hello World! My SpringMVC!");
        return "HelloWorld";
    }
}
