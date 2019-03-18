package com.gzs.learn.backend.admin;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.gzs.learn.backend.admin.core.shiro.filter.KickoutSessionFilter;
import com.gzs.learn.backend.admin.core.shiro.filter.LoginFilter;
import com.gzs.learn.backend.admin.core.shiro.filter.PermissionFilter;
import com.gzs.learn.backend.admin.core.shiro.filter.RoleFilter;
import com.gzs.learn.backend.admin.core.shiro.filter.SimpleAuthFilter;
import com.gzs.learn.backend.admin.utils.SpringContextUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@DependsOn("springContextUtil")
@Slf4j
public class InitService {

    @PostConstruct
    public void initBean() {
        log.info("init service start-------->");
        SecurityUtils.setSecurityManager(SpringContextUtil.getBean(DefaultWebSecurityManager.class));
        log.info("init service end-------->");
    }
}
