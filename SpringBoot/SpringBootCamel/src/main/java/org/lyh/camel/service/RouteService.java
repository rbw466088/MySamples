/**
 * Created By Liu Yuhong - 2017年11月4日
 */
package org.lyh.camel.service;

import javax.annotation.PostConstruct;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**<pre>
 * 路由服务接口
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月4日
 */
//@Component
public class RouteService {

    Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    CamelContext camelContext;

    @Autowired
    ActiveMQComponent activeMQComponent;

    @Autowired
    JmsComponent jmsComponent;

    @PostConstruct
    private void initCamelContext() throws Exception {
        camelContext.addComponent("activemq", activeMQComponent);
        camelContext.addComponent("jms", jmsComponent);
        camelContext.start();
    }

    /**
     * <pre>
     * 启动路由
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月4日
     * @param routeId
     * @throws Exception
     */
    public void startRoute(String routeId) throws Exception {
        camelContext.startRoute(routeId);
        logger.info("路由【{}】状态：{}", routeId, camelContext.getRouteStatus(routeId));
        logger.info("启动后，路由数量：{}", camelContext.getRoutes().size());
    }

    /**
     * <pre>
     * 停止路由
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月4日
     * @param routeId
     * @throws Exception
     */
    public void stopRoute(String routeId) throws Exception {
        camelContext.stopRoute(routeId);
        logger.info("路由【{}】状态：{}", routeId, camelContext.getRouteStatus(routeId));
        logger.info("停止后，路由数量：{}", camelContext.getRoutes().size());
    }

    /**
     * <pre>
     * 删除路由
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月4日
     * @param routeId
     * @throws Exception
     */
    public void removeRoute(String routeId) throws Exception {
        logger.info("移除前，路由【{}】状态：{}", routeId, camelContext.getRouteStatus(routeId));
        logger.info("移除前，路由数量：{}", camelContext.getRoutes().size());
        RouteDefinition rd = camelContext.getRouteDefinition(routeId);
        camelContext.removeRouteDefinition(rd);
        logger.info("移除后，路由【{}】状态：{}", routeId, camelContext.getRouteStatus(routeId));
        logger.info("移除后，路由数量：{}", camelContext.getRoutes().size());
    }

    /**
     * <pre>
     * 取得测试消息
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月5日
     * @return
     */
    public String getMessage() {
        return "Processor To MQ !";
    }
}
