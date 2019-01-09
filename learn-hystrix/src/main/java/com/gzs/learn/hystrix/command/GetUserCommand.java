package com.gzs.learn.hystrix.command;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Component
public class GetUserCommand extends HystrixCommand<String> {

    protected GetUserCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected String run() throws Exception {
        return "user";
    }

}
