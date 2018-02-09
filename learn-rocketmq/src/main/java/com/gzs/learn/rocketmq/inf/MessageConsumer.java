package com.gzs.learn.rocketmq.inf;

import org.springframework.stereotype.Component;

import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;

@Component
public interface MessageConsumer {
    /**
     * 消费消息
     *
     * @param msg
     * @return
     */
    MQMessageStatus consume(MQMessage msg);

    /**
     * 获取消费者nameServer
     *
     * @return
     */
    String getNameServer();
}
