package com.gzs.learn.rocketmq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * rocketmq 消费注解
 *
 * @author guanzhisong
 * @date 2017年12月29日
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RocketMQConsumer {
    /**
     * 实例名
     *
     * @return
     */
    String instance() default "";

    /**
     * 订阅的topic
     *
     * @return
     */
    String topic() default "";

    /**
     * 子topic(tag)
     *
     * @return
     */
    String tag() default "";

    /**
     * consumer group
     *
     * @return
     */
    String group() default "";
}
