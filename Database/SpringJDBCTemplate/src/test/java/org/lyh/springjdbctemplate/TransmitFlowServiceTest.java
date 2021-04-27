/**
 * Created By Liu Yuhong - 2017年9月28日<br />
 */
package org.lyh.springjdbctemplate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年9月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TransmitFlowServiceTest {

    @Autowired
    TransmitFlowService service;
    
    @Value("${spring.datasource.url}")
    String propertyValue;

    @Test
    public void test1() {
        String tableName = service.generateHisTransmitFlowTableNameOfCurrentMonth();
        service.isHisTransmitFlowTableOfCurrentMonthExist(tableName);
        assertTrue(true);
        System.out.println("propertyValue: " + propertyValue);
    }
}
