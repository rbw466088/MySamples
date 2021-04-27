/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月20日<br />
 */
package org.lyh.multidatabase.mapper;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyh.springbootmybatis.Application;
import org.lyh.springbootmybatis.entity.APIMgrSysinfoExample;
import org.lyh.springbootmybatis.mapper.APIMgrSysinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class APIMgrSysinfoMapperTest {

    @Autowired
    private APIMgrSysinfoMapper mapper;

    @Test
    public void testCount() throws Exception {
        int count = mapper.countByExample(new APIMgrSysinfoExample());

        System.out.println("count: " + count);
        assertTrue(true);
    }
}
