/**
 * Created By Liu Yuhong - 2017年11月3日
 */
package org.lyh.staticresources.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月3日
 */
@Controller
public class RootController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/meta")
    public String metaResourcesPage() {
        return "meta";
    }

    @RequestMapping("/static")
    public String staticPage() {
        return "static";
    }

    @RequestMapping("/public")
    public String publicPage() {
        return "public";
    }

    @RequestMapping("/resources")
    public String resourcesPage() {
        return "resources";
    }
}
