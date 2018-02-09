package com.gzs.learn.rocketmq.common;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author guanzhisong
 * @date 2018年1月2日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MQMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    // msgid无需设置,用于接受消息发送后的消息id
    private String msgId;

    // 消息key
    private String key;

    // topic
    private String topic;

    // tag
    private String tag;

    // 发送数据
    private String data;

    // 自定义发送属性map
    private Map<String, String> properties;

    public MQMessage(String key, String topic, String tag, String data) {
        this(null, key, topic, tag, data, null);
    }
}
