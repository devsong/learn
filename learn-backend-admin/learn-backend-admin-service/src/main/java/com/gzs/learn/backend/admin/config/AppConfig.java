package com.gzs.learn.backend.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    private String name;

    private ShiroConfig shiro;

    @Data
    public static class ShiroConfig {
        // 登录url
        private String loginUrl;
        // 登录成功url
        private String successUrl;
        // 未授权url
        private String unauthorizedUrl;

        // session cookie名称
        private String smCookieName;
        private Integer smMaxAge;

        private String rmCookieName;
        private Integer rmMaxAge;

        private String cookieCipher;
        private String cookieDomain;
        private Long sessionTimeout;
    }
}
