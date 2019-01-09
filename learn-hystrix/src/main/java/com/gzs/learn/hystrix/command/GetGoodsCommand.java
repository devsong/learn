package com.gzs.learn.hystrix.command;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GetGoodsCommand extends HystrixCommand<String> {
    RateLimiter rateLimiter;

    public GetGoodsCommand(String group, RateLimiter limiter) {
        super(HystrixCommandGroupKey.Factory.asKey(group));
        rateLimiter = limiter;
    }

    @Override
    protected String run() throws Exception {
        rateLimiter.acquire();
        return "goods";
    }

}
