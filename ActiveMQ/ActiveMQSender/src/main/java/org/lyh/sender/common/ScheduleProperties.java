/**
 * Created By Liu Yuhong - 2017年10月16日
 */
package org.lyh.sender.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**<pre>
 * 定时任务配置
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月16日
 */
@Component
@PropertySource("classpath:config/schedule.properties")
public class ScheduleProperties {
    @Value("${schedule.enabled}")
    private String enable;
    @Value("${schedule.send}")
    private String send;

    public String getEnable() {
        return enable;
    }
    public void setEnable(String enable) {
        this.enable = enable;
    }
    public String getSend() {
        return send;
    }
    public void setSend(String send) {
        this.send = send;
    }

}
