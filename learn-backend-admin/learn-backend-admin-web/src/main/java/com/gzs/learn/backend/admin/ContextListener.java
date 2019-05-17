package com.gzs.learn.backend.admin;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

@WebListener("contextListener")
@Slf4j
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {

    }

    public void contextDestroyed(ServletContextEvent sce) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    log.info("Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    log.error("Error deregistering JDBC driver {}", driver, ex);
                }
            } else {
                log.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
            }
        }
    }
}
