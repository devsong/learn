package com.gzs.learn.backend.admin.core.shiro.listenter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.core.shiro.session.ShiroSessionRepository;

import lombok.extern.slf4j.Slf4j;

@Component("customSessionListener")
@Slf4j
public class CustomSessionListener implements SessionListener {

    @Autowired
    private ShiroSessionRepository shiroSessionRepository;

    @Override
    public void onStart(Session session) {
        log.info("on start");
    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        log.info("on stop");
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }
}
