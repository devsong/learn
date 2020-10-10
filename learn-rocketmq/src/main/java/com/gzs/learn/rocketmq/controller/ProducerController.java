package com.gzs.learn.rocketmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gzs.learn.rocketmq.business.DataProducer;
import com.gzs.learn.rocketmq.business.MsgBody;
import com.gzs.learn.rocketmq.common.MQMessage;

@Controller
@RequestMapping("producer")
public class ProducerController {

    @Autowired
    private DataProducer dataProducer;

    @ResponseBody
    @RequestMapping("send")
    public Object sendMsg(int size) {
        for (int i = 0; i < size; i++) {
            MsgBody body = new MsgBody(i, "test" + i, "score" + i);
            MQMessage msg = new MQMessage("key" + i, "test", "tag", JSON.toJSONString(body));
            dataProducer.send(msg);
        }
        return "success";
    }
}
