package com.gzs.learn.backend.admin;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

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

    @Bean
    @ConditionalOnProperty(name = "spring.aop.auto", havingValue = "false")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}
