package com.gzs.learn.backend.admin.core.shiro.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.common.INI4j;
import com.gzs.learn.backend.admin.core.shiro.service.ShiroManager;
import com.gzs.learn.backend.admin.utils.LoggerUtils;

@Component("shiroManager")
public class ShiroManagerImpl implements ShiroManager {
    // 注意/r/n前不能有空格
    private static final String CRLF = "\r\n";

    @Override
    public String loadFilterChainDefinitions() {
        StringBuffer sb = new StringBuffer();
        sb.append(getFixedAuthRule());// 固定权限，采用读取配置文件
        return sb.toString();
    }

    /**
     * 从配额文件获取固定权限验证规则串
     */
    private String getFixedAuthRule() {
        String fileName = "shiro_base_auth.ini";
        ClassPathResource cp = new ClassPathResource(fileName);
        INI4j ini = null;
        try {
            ini = new INI4j(cp.getFile());
        } catch (IOException e) {
            LoggerUtils.fmtError(getClass(), e, "加载文件出错。file:[%s]", fileName);
        }
        String section = "base_auth";
        Set<String> keys = ini.get(section).keySet();
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            String value = ini.get(section, key);
            sb.append(key).append("=").append(value).append(CRLF);
        }

        return sb.toString();

    }

    // 此方法加同步锁
    // @Override
    // public synchronized void reCreateFilterChains() {
    // AbstractShiroFilter shiroFilter = SpringContextUtil.getBean(ShiroFilter.class);
    // try {
    // shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
    // } catch (Exception e) {
    // log.error("get shiro filter error", e);
    // return;
    // }
    //
    // PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
    // DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
    //
    // // 清空老的权限控制
    // manager.getFilterChains().clear();
    //
    // shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
    // shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());
    // // 重新构建生成
    // Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
    // for (Map.Entry<String, String> entry : chains.entrySet()) {
    // String url = entry.getKey();
    // String chainDefinition = entry.getValue().trim().replace(" ", "");
    // manager.createChain(url, chainDefinition);
    // }
    // }

    @Override
    public Map<String, String> loadFilterChainDefinitionsForSpringBoot() {
        String fileName = "shiro_base_auth.ini";
        ClassPathResource cp = new ClassPathResource(fileName);
        INI4j ini = null;
        try {
            ini = new INI4j(cp.getFile());
        } catch (IOException e) {
            LoggerUtils.fmtError(getClass(), e, "加载文件出错。file:[%s]", fileName);
        }
        String section = "base_auth";
        return ini.get(section);
    }
}