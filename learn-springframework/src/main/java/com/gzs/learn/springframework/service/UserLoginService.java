package com.gzs.learn.springframework.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.learn.springframework.common.DSEnum;
import com.gzs.learn.springframework.common.DataSource;
import com.gzs.learn.springframework.dao.logs.LoginLogMapper;
import com.gzs.learn.springframework.model.LoginLog;

@DataSource(value = DSEnum.DATA_SOURCE_LOGS)
@Component
@Transactional
public class UserLoginService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    public void insert() {
        LoginLog log = new LoginLog();
        log.setCreatetime(new Date());
        log.setIp("123");
        log.setMessage("test111");
        log.setTimestamp(new Date());
        log.setUserid(0L);
        loginLogMapper.insertSelective(log);
    }
}
