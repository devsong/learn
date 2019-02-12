package com.gzs.learn.hystrix.command;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class GetGoodsCommand extends HystrixCommand<String> {
    RateLimiter rateLimiter;

    public GetGoodsCommand(String group, RateLimiter limiter) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(group)).andThreadPoolPropertiesDefaults(
                HystrixThreadPoolProperties.Setter().withCoreSize(20).withMaximumSize(50).withMaxQueueSize(100)));
        rateLimiter = limiter;
    }

    @Override
    protected String run() throws Exception {
        rateLimiter.acquire();
        return "goods";
    }

}
