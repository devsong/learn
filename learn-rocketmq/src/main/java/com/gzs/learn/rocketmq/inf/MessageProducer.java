package com.gzs.learn.rocketmq.inf;

import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;

public interface MessageProducer {
    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    MQMessageStatus send(MQMessage msg);

    /**
     * 获取发送消息的producer
     *
     * @return
     */
    String getNameServer();
}
