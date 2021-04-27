package com.gzs.learn.rocketmq.common;

/**
 * 详细定义需要参照服务端延时定义,此处根据枚举的ordinal值与服务器端对应
 */
public enum DelayLevel {
    /**
     * 实时消息
     */
    REAL_TIME,
    /**
     * 延迟3秒
     */
    SEC_3, SEC_6, SEC_10, SEC_30,
    /**
     * 延时1分钟
     */
    MIN_1, MIN_2, MIN_3, MIN_4, MIN_5, MIN_6, MIN_7, MIN_8, MIN_9, MIN_10, MIN_20, MIN_30,
    /**
     * 延时1小时
     */
    HOUR_1, HOUR_2;
}
