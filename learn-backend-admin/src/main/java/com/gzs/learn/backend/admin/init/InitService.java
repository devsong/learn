package com.gzs.learn.backend.admin.init;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitService {
    @Autowired
    DefaultWebSecurityManager securityManager;

    @PostConstruct
    public void init() {
        SecurityUtils.setSecurityManager(securityManager);
    }
}
