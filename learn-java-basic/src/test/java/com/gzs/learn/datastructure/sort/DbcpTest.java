package com.gzs.learn.datastructure.sort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

public class DbcpTest {

    static final String URL = "jdbc:mysql://127.0.0.1:3306/abel_guns";
    static final String USERNAME = "admin";
    static final String PWD = "1RNbTI@m$zIE114s";

    @Test
    public void testDbcpInit() {

        try (BasicDataSource basicDataSource = new BasicDataSource()) {
            basicDataSource.setUrl(URL);
            basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            basicDataSource.setUsername(USERNAME);
            basicDataSource.setPassword(PWD);
            basicDataSource.setMaxTotal(10);
            basicDataSource.setMinIdle(3);
            basicDataSource.setInitialSize(5);
            basicDataSource.setTestOnBorrow(true);
            basicDataSource.setTestWhileIdle(true);
            basicDataSource.setMaxIdle(5);
            basicDataSource.setTimeBetweenEvictionRunsMillis(30 * 1000);
            basicDataSource.setEnableAutoCommitOnReturn(true);
            basicDataSource.setValidationQuery("select now()");
            basicDataSource.setMaxWaitMillis(10 * 1000);

            Connection connection = basicDataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("select now()");
            ResultSet resultSet = ps.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (resultSet.next()) {
                Timestamp time = resultSet.getTimestamp(1);
                System.out.println(sdf.format(time));
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
