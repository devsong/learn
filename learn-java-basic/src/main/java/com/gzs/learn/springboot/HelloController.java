package com.gzs.learn.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.springboot.cache.UserMapper;

@RestController
public class HelloController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("hello/{id}")
    public String sayHello(@PathVariable("id") Integer id) {
        userMapper.getUserById(id);
        return "hello";
    }
}
