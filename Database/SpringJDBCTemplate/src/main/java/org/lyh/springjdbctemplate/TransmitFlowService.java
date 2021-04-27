/**
 * Created By Liu Yuhong - 2017年9月27日<br />
 */
package org.lyh.springjdbctemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 结转流水处理
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年9月27日
 */
@Service
public class TransmitFlowService {

    private static Logger logger = LoggerFactory.getLogger(TransmitFlowService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        TransmitFlowService service = new TransmitFlowService();
        service.deleteOldNormalTransmitFlow();
    }

    /**
     * <pre>
     * 保存异常结转流水记录
     * </pre>
     * @return 异常结转流水记录id
     */
    public String saveAbnormalTransmitFlow() {
        logger.info("保存异常结转流水记录 开始");

        String closeCheckSql = "SET @ORIG_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0";
        String sql = "INSERT INTO flow_abnormal_transmit VALUES(REPLACE (UUID(), '-', ''),CONCAT(CURRENT_DATE (),' ',CURRENT_TIME ()),'1','2','3','4','5','6','7','8','9','10','11')";
        String openCheckSql = "SET @ORIG_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1";

        jdbcTemplate.update(closeCheckSql);
        jdbcTemplate.update(sql);
        jdbcTemplate.update(openCheckSql);

        logger.info("保存异常结转流水记录 结束");
        return "";
    }

    /**
     * <pre>
     * 保存结转流水记录
     * 1，保存记录到结转流水表【flow_normal_transmit】
     * 2，检索本月的历史结转流水表【flow_his_normal_transmit_[year]_[month]】
     *    如果表不存在：按照月份生成历史结转流水表，表名格式为【flow_his_normal_transmit_】加上【年_月】后缀
     * 3，保存记录到历史结转流水表
     * 4，删除上个月之前的结转流水记录
     * </pre>
     * @return 结转流水记录id
     */
    public String saveTransmitFlow() {
        logger.info("保存结转流水记录 开始");
        // step 1
        insertTransmitFlow();

        // step2
        String tableNameOfCurrentMonth = generateHisTransmitFlowTableNameOfCurrentMonth();
        if (!isHisTransmitFlowTableOfCurrentMonthExist(tableNameOfCurrentMonth)) {
            createHisTransmitFlowTableOfCurrentMonth(tableNameOfCurrentMonth);
        }

        // step3
        insertHisTransmitFlow(tableNameOfCurrentMonth);

        // step4
        deleteOldNormalTransmitFlow();

        logger.info("保存结转流水记录 结束");
        return "";
    }

    /**
     * <pre>
     * 添加结转流水记录
     * </pre>
     * @return
     */
    public String insertTransmitFlow() {
        logger.info("添加结转流水记录 开始");
        //        String closeCheckSql = "SET FOREIGN_KEY_CHECKS = 0";
        //        String sql = "INSERT INTO flow_normal_transmit VALUES (REPLACE (UUID(), '-', ''),'1',CONCAT(CURRENT_DATE (),' ',CURRENT_TIME ()),'2','3','4','5','6','7',1,'9','10','11','12','13')";
        //        String openCheckSql = "SET FOREIGN_KEY_CHECKS = 1";

        logger.info("添加结转流水记录 结束");
        return "";
    }

    /**
     * <pre>
     *  添加历史结转流水记录
     * </pre>
     * @param tableNameOfCurrentMonth 本月历史结转流水表名
     * @return
     */
    public String insertHisTransmitFlow(String tableNameOfCurrentMonth) {
        logger.info("添加历史结转流水记录 开始");
        //      String closeCheckSql = "SET FOREIGN_KEY_CHECKS = 0";
        //      String sql = "INSERT INTO flow_his_normal_transmit VALUES (REPLACE (UUID(), '-', ''),CONCAT(CURRENT_DATE (),' ',CURRENT_TIME ()),'1','2','3','4','5','6','7',1,'9','10','11','12','13')";
        //      String openCheckSql = "SET FOREIGN_KEY_CHECKS = 1";
        logger.info("添加历史结转流水记录 结束");
        return "";
    }

    /**
     * <pre>
     * 检查本月历史结转流水表是否存在
     * </pre>
     * @param tableNameOfCurrentMonth 本月历史结转流水表名
     * @return true：存在，false：不存在
     */
    public Boolean isHisTransmitFlowTableOfCurrentMonthExist(String tableNameOfCurrentMonth) {
        logger.info("检查本月历史结转流水表是否存在 开始");
        String sql = "SELECT table_name from information_schema.tables WHERE table_name='" + tableNameOfCurrentMonth + "'";

        logger.info(sql);
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        if (resultList.size() > 0) {
            logger.info("找到本月历史结转流水表");
            return true;
        } else {
            logger.info("未找到本月历史结转流水表");
            return false;
        }
    }

    /**
     * <pre>
     * 生成本月历史结转流水表名
     * </pre>
     * @return
     */
    public String generateHisTransmitFlowTableNameOfCurrentMonth() {
        logger.info("生成本月历史结转流水表名 开始");
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM");

        String tableName = "flow_his_normal_transmit_" + format.format(now.getTime());
        logger.info("本月历史结转流水表名: " + tableName);
        return tableName;
    }

    /**
     * <pre>
     * 取上一月的历史结转表名
     * </pre>
     * @param normalTransmitFlowTableName 历史结转表名
     * @return 上一月的历史结转表名
     */
    public String getHisTransmitFlowTableNameOfPreviousMonth(String hisTransmitFlowTableName) {
        logger.info("取上一月的历史结转表名 开始");
        String tableProfix = hisTransmitFlowTableName.substring(0, 25);
        String date = hisTransmitFlowTableName.substring(25);

        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM");
        try {
            now.setTime(format.parse(date));
            now.add(Calendar.MONTH, -1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String tableName = tableProfix + format.format(now.getTime());
        logger.info("上一月的历史结转表名: " + tableName);
        return tableName;
    }

    /**
     * <pre>
     * 创建历史结转流水表
     * </pre>
     * @param tableNameOfCurrentMonth 历史结转流水表名
     */
    public void createHisTransmitFlowTableOfCurrentMonth(String tableNameOfCurrentMonth) {
        logger.info("创建历史结转流水表: " + tableNameOfCurrentMonth + " 开始");

        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE " + tableNameOfCurrentMonth + " (");
        sql.append("`uuid` varchar(32) COLLATE utf8_bin NOT NULL,");
        sql.append("`create_time` varchar(32) COLLATE utf8_bin NOT NULL,");
        sql.append("`file_absolute_path` varchar(255) COLLATE utf8_bin NOT NULL,");
        sql.append("`file_md` varchar(255) COLLATE utf8_bin NOT NULL,");
        sql.append("`file_relative_path` varchar(255) COLLATE utf8_bin NOT NULL,");
        sql.append("`file_size` bigint(20) NOT NULL,");
        sql.append("`his_collect_id` varchar(32) COLLATE utf8_bin NOT NULL,");
        sql.append("`his_collect_table` varchar(255) COLLATE utf8_bin NOT NULL,");
        sql.append("`send_batch_number` bigint(20) NOT NULL,");
        sql.append("`update_field` varchar(255) COLLATE utf8_bin NOT NULL,");
        sql.append("`collect_batch_number` bigint(20) NOT NULL,");
        sql.append("`pre_batch_number` bigint(20) NOT NULL,");
        sql.append("`subscribe_standard_id` varchar(32) COLLATE utf8_bin NOT NULL,");
        sql.append("PRIMARY KEY (`uuid`),");
        sql.append("KEY `fk_" + tableNameOfCurrentMonth + "_subscribe_standard_id` (`subscribe_standard_id`),");
        sql.append("CONSTRAINT `fk_" + tableNameOfCurrentMonth
                + "_subscribe_standard_id` FOREIGN KEY (`subscribe_standard_id`) REFERENCES `mgr_subscribe_standard` (`uuid`)");
        sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin");

        logger.info(sql.toString());
        jdbcTemplate.execute(sql.toString());

        logger.info("创建历史结转流水表: " + tableNameOfCurrentMonth + " 结束");
    }

    /**
     * <pre>
     * 删除结转流水表里上个月之前的记录，只保留上个月至今的记录
     * </pre>
     */
    public void deleteOldNormalTransmitFlow() {
        logger.info("删除结转流水表里上个月之前的记录 开始");
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(now.getTime()) + "-01 00:00:00";
        logger.info(date);
        String sql = "delete from flow_normal_transmit where create_time < '" + date + "'";

        jdbcTemplate.execute(sql);
        logger.info("删除结转流水表里上个月之前的记录 结束");
    }
}
