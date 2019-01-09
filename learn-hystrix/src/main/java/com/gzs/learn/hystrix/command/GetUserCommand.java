package com.gzs.learn.hystrix.command;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GetUserCommand extends HystrixCommand<String> {
    RateLimiter rateLimiter;

    public GetUserCommand(String group, RateLimiter limiter) {
        super(HystrixCommandGroupKey.Factory.asKey(group));
        rateLimiter = limiter;
    }

    @Override
    protected String run() throws Exception {
        rateLimiter.acquire(1);
        return "user";
    }

}
