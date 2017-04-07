package com.gzs.learn.nio;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CostLogHandler {

    @Around("@annotation(com.gzs.learn.nio.CostLog)")
    public Object logTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println("cost time:" + cost);
        return object;
    }
}
