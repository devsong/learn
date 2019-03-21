package com.gzs.learn.backend.admin.core.shiro.cache.impl;

import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.core.shiro.cache.JedisManager;
import com.gzs.learn.backend.admin.core.shiro.cache.JedisShiroCache;
import com.gzs.learn.backend.admin.core.shiro.cache.ShiroCacheManager;

@Component
public class JedisShiroCacheManager implements ShiroCacheManager {
    @Autowired
    @Lazy
    private JedisManager jedisManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }

    @Override
    public void destroy() {
        // 如果和其他系统，或者应用在一起就不能关闭
        // getJedisManager().getJedis().shutdown();
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
