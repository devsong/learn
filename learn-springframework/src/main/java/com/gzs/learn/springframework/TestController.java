package com.gzs.learn.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzs.learn.springframework.service.UserLoginService;
import com.gzs.learn.springframework.service.UserService;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("insert")
    public void testInsert() {
        userService.insert();
        userLoginService.insert();
    }
}
