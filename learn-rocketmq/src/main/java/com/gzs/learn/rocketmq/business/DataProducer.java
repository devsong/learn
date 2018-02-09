package com.gzs.learn.rocketmq.business;

import com.gzs.learn.rocketmq.annotation.RocketMQProducer;
import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;
import com.gzs.learn.rocketmq.inf.MessageProducer;

@RocketMQProducer(instance = "test", group = "test-producer", topic = "test")
public class DataProducer implements MessageProducer {

    @Override
    public MQMessageStatus send(MQMessage msg) {
        return null;
    }

    @Override
    public String getNameServer() {
        return null;
    }
}
