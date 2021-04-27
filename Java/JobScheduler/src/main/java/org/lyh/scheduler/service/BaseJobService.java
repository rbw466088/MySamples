/**
 * Created By Liu Yuhong - 2017年10月23日
 */
package org.lyh.scheduler.service;

import org.springframework.stereotype.Component;

/**<pre>
 * 所有Service需实现的父类接口
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年10月23日
 */
@Component
public interface BaseJobService {

    /**
     * <pre>
     * 传入需处理的对象，调用Service
     * </pre>
     * @param record
     */
    boolean executeService(Object record);
}
