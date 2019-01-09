package com.gzs.learn.hystrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.util.concurrent.RateLimiter;
import com.gzs.learn.hystrix.command.GetGoodsCommand;
import com.gzs.learn.hystrix.command.GetOrderCommand;
import com.gzs.learn.hystrix.command.GetUserCommand;

@Controller
public class ServiceController {
    private static final String GROUP = "group";

    @RequestMapping("all")
    @ResponseBody
    public String all() {
        RateLimiter goodsLimiter = RateLimiter.create(100);
        RateLimiter userLimiter = RateLimiter.create(100);
        RateLimiter orderLimiter = RateLimiter.create(100);
        GetGoodsCommand goodsCommand = new GetGoodsCommand(GROUP,goodsLimiter);
        GetUserCommand userCommand = new GetUserCommand(GROUP,userLimiter);
        GetOrderCommand orderCommand = new GetOrderCommand(GROUP,orderLimiter);
        String user = userCommand.execute();
        String order = orderCommand.execute();
        String goods = goodsCommand.execute();
        return user + ":" + order + ":" + goods;
    }
}
