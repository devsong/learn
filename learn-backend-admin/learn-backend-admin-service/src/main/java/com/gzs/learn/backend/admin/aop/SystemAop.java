package com.gzs.learn.backend.admin.aop;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class SystemAop implements Ordered {
    @Around(value = "recordController()")
    public Object processController(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return point.proceed();
        } finally {
            stopwatch.stop();
            log.info("controller method:{},cost:{}", methodName, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void recordController() throws Throwable {
    }

    @Around(value = "recorPerf()")
    public Object processPerf(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return point.proceed();
        } finally {
            stopwatch.stop();
            log.info("performance aop method:{},cost:{}", methodName, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    @Pointcut("within(@com.gzs.learn.backend.admin.annotation.PerformanceAnnotation *)")
    public void recorPerf() throws Throwable {
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
