package com.gzs.learn.backend.admin.core.shiro.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.common.Consts;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.utils.SerializeUtil;
import com.gzs.learn.backend.admin.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Component
@Slf4j
public class JedisManager {
    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisConnectionException e) {
            String message = StringUtils.trim(e.getMessage());
            if ("Could not get a resource from the pool".equalsIgnoreCase(message)) {
                log.info("++++++++++请检查你的redis服务++++++++");
                log.info("|①.请检查是否安装redis服务，如果没安装，Windos 请参考Blog：http://www.sojson.com/blog/110.html|");
                log.info("|②.请检查redis 服务是否启动。启动口诀[安装目录中的redis-server.exe，双击即可，如果有错误，请用CMD方式启动，怎么启动百度，或者加QQ群。]|");
                log.info("|③.请检查redis启动是否带配置文件启动，也就是是否有密码，是否端口有变化（默认6379）。解决方案，参考第二点。如果需要配置密码和改变端口，请修改spring-cache.xml配置。|");
                log.info("|④.QQ群：259217951，目前需要付费，免费的方案请参考链接：http://www.sojson.com/shiro");

                log.info("|PS.如果对Redis表示排斥，请使用Ehcache版本：http://www.sojson.com/jc_shiro_ssm_ehcache.html");
                log.info("项目退出中....生产环境中，请删除这些东西。我来自。JedisManage.java line:53");
                System.exit(0);// 停止项目
            }
            throw new JedisConnectionException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jedis;
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        byte[] result = null;
        try (Jedis jedis = getJedis()) {
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        try (Jedis jedis = getJedis()) {
            jedis.select(dbIndex);
            Long result = jedis.del(key);
            LoggerUtils.fmtDebug(getClass(), "删除Session结果：%s", result);
        } catch (Exception e) {
            throw e;
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
        try (Jedis jedis = getJedis()) {
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Session> AllSession(int dbIndex, String redisShiroSession) throws Exception {
        Set<Session> sessions = new HashSet<Session>();
        try (Jedis jedis = getJedis()) {
            jedis.select(dbIndex);
            Set<byte[]> byteKeys = jedis.keys((Consts.REDIS_SHIRO_ALL).getBytes());
            if (byteKeys != null && byteKeys.size() > 0) {
                for (byte[] bs : byteKeys) {
                    Session obj = SerializeUtil.deserialize(jedis.get(bs), Session.class);
                    if (obj instanceof Session) {
                        sessions.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return sessions;
    }
}
