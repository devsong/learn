package com.gzs.learn.springcloud.controller;

import com.gzs.learn.springcloud.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
@RequiredArgsConstructor()
public class ExampleController {


    @PutMapping("create-user")
    public Object createUser(@RequestBody User user){
        return null;
    }
}
