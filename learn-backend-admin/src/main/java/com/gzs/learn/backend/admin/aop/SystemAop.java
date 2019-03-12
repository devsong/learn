package com.gzs.learn.backend.admin.aop;

import static com.gzs.learn.backend.admin.common.ResponseCode.INTERNAL_ERROR;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gzs.learn.backend.admin.common.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@Order(1)
public class SystemAop {
    @Around("@annotation(org.springframework.stereotype.Controller))")
    public Object handle(ProceedingJoinPoint joinPoint) {
        Object args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        try {
            log.info("before controller");
            return joinPoint.proceed();
        } catch (Throwable e) {
            // 系统错误日志
            log.error("SystemError method:{}-{},args:{}", signature.getDeclaringType(), signature.getName(), JSON.toJSONString(args), e);
            return CommonResponse.build(INTERNAL_ERROR.getCode(), INTERNAL_ERROR.getDesc(), "");
        }
    }
}
