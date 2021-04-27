/**
 * Created By Liu Yuhong - 2017年11月6日
 */
package org.lyh.springbootws.service;

import java.util.Date;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月6日
 */
public interface HumanResourceService {

    void bookHoliday(Date startDate, Date endDate, String name);

}