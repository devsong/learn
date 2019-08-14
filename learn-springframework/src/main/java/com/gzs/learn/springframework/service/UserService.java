package com.gzs.learn.springframework.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzs.learn.springframework.common.DSEnum;
import com.gzs.learn.springframework.common.DataSource;
import com.gzs.learn.springframework.dao.UserMapper;
import com.gzs.learn.springframework.model.User;

@DataSource(value = DSEnum.DATA_SOURCE_GUNS)
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert() {
        User user = new User();
        user.setAccount("gzsfdfd");
        user.setAvatar("");
        user.setBirthday(new Date());
        user.setDeptid(1);
        user.setSalt("1121");
        userMapper.insertSelective(user);
    }
}
