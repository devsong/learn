package com.gzs.learn.hystrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {
    @RequestMapping("all")
    @ResponseBody
    public String all() {
        return "all";
    }
}
