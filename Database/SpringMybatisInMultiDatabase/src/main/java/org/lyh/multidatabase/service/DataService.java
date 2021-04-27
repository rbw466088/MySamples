/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月20日<br />
 */
package org.lyh.multidatabase.service;

import org.lyh.multidatabase.entity.APIMgrSysinfoExample;
import org.lyh.multidatabase.entity.UserExample;
import org.lyh.multidatabase.mapper.master.UserMapper;
import org.lyh.multidatabase.mapper.second.APIMgrSysinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class DataService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private APIMgrSysinfoMapper apiMgrSysinfoMapper;

    public int getCount() {
        return userMapper.countByExample(new UserExample());
    }

    public int getCount2() {
      return apiMgrSysinfoMapper.countByExample(new APIMgrSysinfoExample());
  }
}
