/**
 * Created By Liu Yuhong - 2017年9月27日<br />
 */
package org.lyh.springjdbctemplate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * <pre>
 * JdbcTemplate工具类
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年9月27日
 */
public class JdbcTemplateUtil {

    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            if (dataSource == null) {
                dataSource = initDataSource();
            }
            jdbcTemplate = new JdbcTemplate(dataSource);
        }

        return jdbcTemplate;
    }

    private static DataSource initDataSource() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String dataSourceUrl = "jdbc:mysql://10.18.27.217:3306/data-stage?useUnicode=true&amp;characterEncoding=utf8";
        String userName = "root";
        String password = "root";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate2() {
        dataSource = initDataSource2();
        jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate;
    }

    private static DataSource initDataSource2() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String dataSourceUrl = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&amp;characterEncoding=utf8";
        String userName = "root";
        String password = "root";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
