package com.gzs.learn.rocketmq.business;

import com.alibaba.fastjson.JSON;
import com.gzs.learn.rocketmq.annotation.RocketMQConsumer;
import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;
import com.gzs.learn.rocketmq.inf.MessageConsumer;

import lombok.extern.slf4j.Slf4j;

@RocketMQConsumer(instance = "test", topic = "test", group = "consumer-test")
@Slf4j
public class DataConsumer implements MessageConsumer {

    @Override
    public MQMessageStatus consume(MQMessage msg) {
        try {
            MsgBody body = JSON.parseObject(msg.getData(), MsgBody.class);
            log.info("reveive msg [id:{},name:{},score:{}]", body.getId(), body.getName(),
                    body.getScore());
            return MQMessageStatus.SUCCESS;
        } catch (Exception e) {
            return MQMessageStatus.SUCCESS;
        }
    }

    @Override
    public String getNameServer() {
        return null;
    }

}
