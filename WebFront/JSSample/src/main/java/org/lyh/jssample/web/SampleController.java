/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月18日<br />
 */
package org.lyh.jssample.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 */
@RequestMapping(value = "/")
@Controller
@Configuration
public class SampleController {

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/infoRelation").setViewName("forward:/echarts/infoRelation_echarts.html");
                registry.addViewController("/dataRelation").setViewName("forward:/echarts/dataRelation_echarts.html");
                registry.addViewController("/modalDemo").setViewName("forward:/modalDemo.html");
            }
        };
    }

    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String drawingBoard() {
        System.out.println("show drawingBoard page...");
        return "drawingBoard";
    }

}
