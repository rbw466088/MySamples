/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月20日<br />
 */
package org.lyh.multidatabase.mapper;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyh.multidatabase.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void testGetMYSQLDataSourceProperties() {
        try {
            DataSource dataSource = (DataSource) context.getBean("masterDataSource");

            Connection conn = dataSource.getConnection();
            DatabaseMetaData data = conn.getMetaData();
            System.out.println(data.getURL());

            String sql = "show tables";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            String tableName = "";
            System.out.println("Table Name");
            while (rs.next()) {
                tableName = rs.getString(1);
                System.out.println(tableName);
            }
            System.out.println("---------------------------------------------------");

            sql = "show full columns from " + tableName;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("Column Name");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(9));
            }
            System.out.println("---------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCreateNewDataSource() {
        try {
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName("org.apache.tomcat.jdbc.pool.DataSource");
            String driverClassName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "xiaoniu@2016";

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password)
                    .type(dataSourceType);
            DataSource dataSource = factory.build();

            Connection conn = dataSource.getConnection();

            String sql = "show tables";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            String tableName = "";
            System.out.println("Table Name");
            while (rs.next()) {
                tableName = rs.getString(1);
                System.out.println(tableName);
            }
            System.out.println("---------------------------------------------------");

            sql = "show full columns from " + tableName;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("Column Name");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(9));
            }
            System.out.println("---------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(true);
    }

}
