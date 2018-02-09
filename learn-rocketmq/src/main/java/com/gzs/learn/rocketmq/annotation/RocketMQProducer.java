package com.gzs.learn.rocketmq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * rocketmq 发送者注解
 *
 * @author guanzhisong
 * @date 2017年12月29日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RocketMQProducer {
    /**
     * 发送方实例名
     *
     * @return
     */
    String instance() default "";

    /**
     * topic
     *
     * @return
     */
    String topic() default "";

    /**
     * group
     *
     * @return
     */
    String group() default "";
}
