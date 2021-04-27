/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月20日<br />
 */
package org.lyh.multidatabase.web;

import org.lyh.multidatabase.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 */
@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService service;

    @ResponseBody
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public String count() {
        return "count: " + service.getCount();
    }
}
