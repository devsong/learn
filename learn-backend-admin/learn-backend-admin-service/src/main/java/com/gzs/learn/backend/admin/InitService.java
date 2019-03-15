package com.gzs.learn.backend.admin;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@DependsOn("springContextUtil")
@Slf4j
public class InitService {
    @Autowired
    DefaultWebSecurityManager securityManager;

    @PostConstruct
    public void initBean() {
        log.info("init service start-------->");
        SecurityUtils.setSecurityManager(securityManager);
        log.info("init service end-------->");
    }
}
