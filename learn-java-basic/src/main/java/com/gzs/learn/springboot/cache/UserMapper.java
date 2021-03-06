package com.gzs.learn.springboot.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper {
    @Cacheable(value = "user")
    public User getUserById(int id) {
        log.info("load data from datasource");
        return User.datas.get(id);
    }

    @CacheEvict(value = "user", allEntries = false)
    public User deleteUserById(int id) {
        log.info("del data from datasource");
        return User.datas.get(id);
    }
}
