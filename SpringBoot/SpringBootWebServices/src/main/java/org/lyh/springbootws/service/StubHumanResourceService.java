/**
 * Created By Liu Yuhong - 2017年11月6日
 */
package org.lyh.springbootws.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月6日
 */
@Service
public class StubHumanResourceService implements HumanResourceService {

    private final Logger logger = LoggerFactory.getLogger(StubHumanResourceService.class);

    @Override
    public void bookHoliday(Date startDate, Date endDate, String name) {
        this.logger.info("Booking holiday for [{} - {}] for [{}] ", startDate, endDate, name);
    }

}