package com.gzs.learn.backend.admin.core.shiro.cache;

import static com.gzs.learn.backend.admin.common.Consts.DB_INDEX;
import static com.gzs.learn.backend.admin.common.Consts.REDIS_SHIRO_SESSION;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.common.Consts;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.utils.SerializeUtil;
import com.gzs.learn.backend.admin.core.shiro.session.SessionStatus;
import com.gzs.learn.backend.admin.core.shiro.session.ShiroSessionRepository;

@Component("jedisShiroSessionRepository")
public class JedisShiroSessionRepository implements ShiroSessionRepository {
    @Autowired
    private JedisManager jedisManager;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }
        try {
            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));

            // 不存在才添加。
            if (null == session.getAttribute(Consts.SESSION_STATUS)) {
                // Session 踢出自存存储。
                SessionStatus sessionStatus = new SessionStatus();
                session.setAttribute(Consts.SESSION_STATUS, sessionStatus);
            }

            byte[] value = SerializeUtil.serialize(session);

            getJedisManager().saveValueByKey(DB_INDEX, key, value, (int) (session.getTimeout() / 1000));
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "save session error，id:[%s]", session.getId());
        }
    }

    @Override
    public void deleteSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getJedisManager().deleteByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(id)));
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "删除session出现异常，id:[%s]", id);
        }
    }

    @Override
    public Session getSession(Serializable id) {
        if (id == null)
            throw new NullPointerException("session id is empty");
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(id)));
            session = SerializeUtil.deserialize(value, Session.class);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取session异常，id:[%s]", id);
        }
        return session;
    }

    @Override
    public Collection<Session> getAllSessions() {
        Collection<Session> sessions = null;
        try {
            sessions = getJedisManager().AllSession(DB_INDEX, REDIS_SHIRO_SESSION);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取全部session异常");
        }

        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
