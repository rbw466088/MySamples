/**
 * Created By Liu Yuhong - 2017年9月27日<br />
 */
package org.lyh.springjdbctemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年9月27日
 */
@Service
public class HistoryFlowService {

    private static Logger logger = LoggerFactory.getLogger(HistoryFlowService.class);

    @Autowired
    JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getJdbcTemplate();

    public static void main(String[] args) {
        HistoryFlowService service = new HistoryFlowService();
        System.out.println(service.findHistoryFlowTableByTypeAndId(FlowType.FLOW_HIS_COLLECT, "10620299a36911e7b95efc3fdb8711d5"));
    }

    /**
     * <pre>
     * 从各月的历史流水表里查找流水记录
     * 1，取得全部的历史流水表名
     * 2，依次使用记录的uuid，查询历史流水表
     * </pre>
     * @param type 流水类别
     * @param uuid 记录uuid
     * @return String 历史流水表名
     */
    public String findHistoryFlowTableByTypeAndId(FlowType type, String uuid) {
        logger.info("从各月的历史流水表里查找流水记录 开始");

        String queryTables = "SELECT table_name from information_schema.tables WHERE table_schema=database() and table_name LIKE '" + type.toString() + "%'";
        List<Map<String, Object>> tableList = jdbcTemplate.queryForList(queryTables);
        for (int i = 0; i < tableList.size(); i++) {
            Map<String, Object> tableMap = tableList.get(i);
            String tableName = String.valueOf(tableMap.get("table_name"));
            String queryRecord = "select 1 from " + tableName + " where uuid = '" + uuid + "'";
            List<Map<String, Object>> recordList = jdbcTemplate.queryForList(queryRecord);
            if (recordList.size() > 0) {
                logger.info("流水记录所在表：" + tableName);
                return tableName;
            }
        }

        logger.info("未找到流水记录所在表");
        return "";
    }

    /**
     * <pre>
     * 取得历史流水表记录
     * </pre>
     * @param historyFlowTableName 历史流水表名
     * @param uuid 记录uuid
     * @return Map<String, Object> 历史流水表记录
     */
    public Map<String, Object> findHistoryFlowRecordByTypeAndId(String historyFlowTableName, String uuid) {
        logger.info("取得历史流水表记录 开始");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String queryRecord = "select * from " + historyFlowTableName + " where uuid = '" + uuid + "'";
        List<Map<String, Object>> recordList = jdbcTemplate.queryForList(queryRecord);
        if (recordList.size() > 0) {
            resultMap = recordList.get(0);
        }

        logger.info("取得历史流水表记录 结束");
        return resultMap;
    }
}
