package com.gzs.learn.rocketmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.gzs.learn.rocketmq.business.DataProducer;
import com.gzs.learn.rocketmq.business.MsgBody;
import com.gzs.learn.rocketmq.common.MQMessage;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataProducerTest {

    @Autowired
    private DataProducer dataProducer;

    @Test
    public void testSendData() {
        for (int i = 0; i < 100; i++) {
            MsgBody body = new MsgBody(i, "test" + i, "score" + i);
            MQMessage msg = new MQMessage(i + "", "test", "", JSON.toJSONString(body));
            dataProducer.send(msg);
        }
    }
}
