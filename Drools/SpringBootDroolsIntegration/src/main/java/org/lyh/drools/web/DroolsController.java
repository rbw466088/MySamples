/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月18日<br />
 */
package org.lyh.drools.web;

import org.lyh.drools.service.DroolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 */
@RequestMapping("/drools")
@Controller
public class DroolsController {

    @Autowired
    DroolsService service;

    @RequestMapping(value = "/ruleTest", method = RequestMethod.GET)
    public String ruleTest() throws Exception {
        return "ruleTest";
    }

    @ResponseBody
    @RequestMapping(value = "/rule", method = RequestMethod.GET)
    public String rule() throws Exception {
        return service.runByRule();
    }

    @ResponseBody
    @RequestMapping(value = "/simpleTemplate", method = RequestMethod.GET)
    public String simpleTemplate() throws Exception {
        return service.runBySimpleTemplate();
    }

    @ResponseBody
    @RequestMapping(value = "/complexTemplate", method = RequestMethod.GET)
    public String complexTemplate() throws Exception {
        return service.runByComplexTemplate(null, null);
    }

    @ResponseBody
    @RequestMapping(value = "/complexTemplateTest", method = RequestMethod.GET)
    public String complexTemplateTest(@RequestParam("price") String price, @RequestParam("name") String name) throws Exception {
        return service.runByComplexTemplate(price, name);
    }
}
