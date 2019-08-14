package com.gzs.learn.springframework.common;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import com.gzs.learn.springframework.DynamicDataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * MybatisPlus配置
 */
@Configuration
@EnableTransactionManagement(order = 2, proxyTargetClass = true)
// 由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = { "com.gzs.learn.springframework.dao" })
public class MybatisPlusConfig {
    @Autowired
    GunsDataSourceProperties gunsDataSourceProperties;

    @Autowired
    LogDataSourceProperties logDataSourceProperties;

    /**
     * 另一个数据源
     */
    private DruidDataSource dataSourceLogs() {
        DruidDataSource dataSource = new DruidDataSource();
        logDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSourceGuns() {
        DruidDataSource dataSource = new DruidDataSource();
        gunsDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceGuns();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {
        DruidDataSource dataSourceGuns = dataSourceGuns();
        DruidDataSource dataSourceLogs = dataSourceLogs();

        try {
            dataSourceGuns.init();
            dataSourceLogs.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> map = Maps.newHashMap();
        map.put(DSEnum.DATA_SOURCE_GUNS, dataSourceGuns);
        map.put(DSEnum.DATA_SOURCE_LOGS, dataSourceLogs);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }
}
