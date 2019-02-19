package com.gzs.learn.backend.admin.core.shiro.listenter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.core.shiro.session.ShiroSessionRepository;

@Component("customSessionListener")
public class CustomSessionListener implements SessionListener {

    @Autowired
    private ShiroSessionRepository shiroSessionRepository;

    @Override
    public void onStart(Session session) {
        System.out.println("on start");
    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        System.out.println("on stop");
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

}
