package com.gzs.learn.rocketmq.business;

import com.gzs.learn.rocketmq.annotation.RocketMQProducer;
import com.gzs.learn.rocketmq.common.DelayLevel;
import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;
import com.gzs.learn.rocketmq.inf.MessageProducer;
import com.gzs.learn.rocketmq.register.MessageProducerRegister;

@RocketMQProducer(instance = "test", group = "test-producer", topic = "test")
public class DataProducer implements MessageProducer {

    @Override
    public MQMessageStatus send(MQMessage msg) {
        try {
            MQMessageStatus status = MessageProducerRegister.send(DataProducer.class, msg, DelayLevel.REAL_TIME);
            return status;
        } catch (Exception e) {
            return MQMessageStatus.FAIL;
        }
    }

    @Override
    public String getNameServer() {
        return null;
    }
}
