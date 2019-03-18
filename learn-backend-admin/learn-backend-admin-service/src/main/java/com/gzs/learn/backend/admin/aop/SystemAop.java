package com.gzs.learn.backend.admin.aop;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class SystemAop {

    @Around("@annotation(com.gzs.learn.backend.admin.annotation.PerformanceAnnotation)")
    public Object recordCost(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return point.proceed();
        } finally {
            stopwatch.stop();
            log.info("method:{},cost:{}", methodName, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    @Around("@annotation(org.springframework.stereotype.Controller)")
    public Object recordController(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return point.proceed();
        } finally {
            stopwatch.stop();
            log.info("method:{},cost:{}", methodName, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }
}
