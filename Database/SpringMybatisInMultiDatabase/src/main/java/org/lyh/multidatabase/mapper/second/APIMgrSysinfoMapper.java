package org.lyh.multidatabase.mapper.second;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.lyh.multidatabase.entity.APIMgrSysinfo;
import org.lyh.multidatabase.entity.APIMgrSysinfoExample;
import org.springframework.stereotype.Repository;

@Repository
public interface APIMgrSysinfoMapper {
    int countByExample(APIMgrSysinfoExample example);

    int deleteByExample(APIMgrSysinfoExample example);

    int deleteByPrimaryKey(String rowGuid);

    int insert(APIMgrSysinfo record);

    int insertSelective(APIMgrSysinfo record);

    List<APIMgrSysinfo> selectByExample(APIMgrSysinfoExample example);

    APIMgrSysinfo selectByPrimaryKey(String rowGuid);

    int updateByExampleSelective(@Param("record") APIMgrSysinfo record, @Param("example") APIMgrSysinfoExample example);

    int updateByExample(@Param("record") APIMgrSysinfo record, @Param("example") APIMgrSysinfoExample example);

    int updateByPrimaryKeySelective(APIMgrSysinfo record);

    int updateByPrimaryKey(APIMgrSysinfo record);
}