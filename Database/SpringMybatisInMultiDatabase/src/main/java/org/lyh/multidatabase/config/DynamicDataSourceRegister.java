/**
 * <br />
 * DXC Confidential<br />
 * Copyright © 2017 DXC, Inc.<br />
 * <br />
 * Created By Liu Yuhong - 2017年9月23日<br />
 */
package org.lyh.multidatabase.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/** 
 * 动态数据源注册 
 * @author xiyan 
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    // 数据源配置信息  
    private PropertyValues dataSourcePropertyValues;
    // 默认数据源  
    private DataSource defaultDataSource;
    // 动态数据源  
    private Map<String, DataSource> dynamicDataSources = new HashMap<>();

    /** 
     * 加载多数据源配置 
     * Environment 是用来读取application.properties中的内容 
     */
    @Override
    public void setEnvironment(Environment env) {
        //读取application.properties中以jdbc.开头的属性  
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "jdbc.");
        String dsPrefixs = propertyResolver.getProperty("datasources");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源  
            Map<String, Object> map = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = initDataSource(map);
            // 设置默认数据源  
            if ("ds".equals(dsPrefix)) {
                defaultDataSource = ds;
            } else {
                dynamicDataSources.put(dsPrefix, ds);
            }
            dataBinder(ds, env);
        }
    }

    /** 
     * 初始化数据源 
     * @param map 
     * @return 
     */
    public DataSource initDataSource(Map<String, Object> map) {
        String driverClassName = map.get("driverClassName").toString();
        String url = map.get("url").toString();
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //配置最大连接  
        dataSource.setMaxActive(300);
        //配置初始连接  
        dataSource.setInitialSize(20);
        //配置最小连接  
        dataSource.setMinIdle(10);
        //连接等待超时时间  
        dataSource.setMaxWait(60000);
        //间隔多久进行检测,关闭空闲连接  
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //一个连接最小生存时间  
        dataSource.setMinEvictableIdleTimeMillis(300000);
        //连接等待超时时间 单位为毫秒 缺省启用公平锁，  
        //并发效率会有所下降， 如果需要可以通过配置useUnfairLock属性为true使用非公平锁  
        dataSource.setUseLock(true);
        //用来检测是否有效的sql  
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        //申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能  
        dataSource.setTestOnBorrow(false);
        //归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能  
        dataSource.setTestOnReturn(false);
        //打开PSCache,并指定每个连接的PSCache大小启用poolPreparedStatements后，  
        //PreparedStatements 和CallableStatements 都会被缓存起来复用，  
        //即相同逻辑的SQL可以复用一个游标，这样可以减少创建游标的数量。  
//        dataSource.setPoolPreparedStatements(true);
//        dataSource.setMaxOpenPreparedStatements(20);
        try {
            //配置sql监控的filter  
//            dataSource.setFilters("stat,wall,log4j");
//            dataSource.init();
        } catch (Exception e) {
            throw new RuntimeException("druid datasource init fail");
        }
        /*String dsType = map.get("dsType").toString(); 
        Class<DataSource> dataSourceType; 
        DataSource dataSource = null; 
        try { 
            dataSourceType = (Class<DataSource>) Class.forName(dsType); 
        
        
            dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(url) 
                    .username(username).password(password).type(dataSourceType).build();; 
        } catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
        }*/
        return dataSource;
    }

    /** 
     * 加载数据源配置信息 
     * @param dataSource 
     * @param env 
     */
    private void dataBinder(DataSource dataSource, Environment env) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setIgnoreNestedProperties(false);// false  
        dataBinder.setIgnoreInvalidFields(false);// false  
        dataBinder.setIgnoreUnknownFields(true);// true  
        if (dataSourcePropertyValues == null) {
            Map<String, Object> values = new RelaxedPropertyResolver(env, "datasource").getSubProperties(".");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    /** 
     * 注册数据源been 
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中  
        targetDataSources.put("dataSource", defaultDataSource);
        // 添加更多数据源  
        targetDataSources.putAll(dynamicDataSources);

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MasterDataSourceConfig.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("多数据源注册成功");
    }

}