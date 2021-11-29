package com.gzs.learn.springcloud.controller;

import com.gzs.learn.springcloud.entity.User;
import com.gzs.learn.springcloud.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExampleController {

    private final IUserService userService;

    @PutMapping("create-user")
    public Object createUser(@RequestBody User user){
        userService.saveUser(user);
        return "OK";
    }
}
