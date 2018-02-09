package com.gzs.learn.rocketmq.register;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.gzs.learn.rocketmq.annotation.RocketMQProducer;
import com.gzs.learn.rocketmq.common.DelayLevel;
import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;
import com.gzs.learn.rocketmq.inf.MessageProducer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageProducerRegister implements ApplicationContextAware {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private static ApplicationContext ctx;
    private static ConcurrentHashMap<Class<? extends MessageProducer>, DefaultMQProducer> holder =
            new ConcurrentHashMap<>();

    @Value("${com.gzs.learn.rocketmq.nameserver:''}")
    private String defaultNameServer;

    @PostConstruct
    private void initProducer() {
        Map<String, MessageProducer> beanMap = ctx.getBeansOfType(MessageProducer.class);
        for (Entry<String, MessageProducer> entry : beanMap.entrySet()) {
            String beanName = entry.getKey();
            MessageProducer messageProducer = entry.getValue();

            RocketMQProducer an = messageProducer.getClass().getAnnotation(RocketMQProducer.class);
            if (an == null) {
                continue;
            }
            DefaultMQProducer producer = new DefaultMQProducer();
            // 以接口配置的nameServer为第一优先级
            String nameServer = messageProducer.getNameServer();
            if (StringUtils.isBlank(nameServer)) {
                // 配置文件环境变量为第二优先级
                nameServer = defaultNameServer;
            }

            if (StringUtils.isBlank(nameServer)) {
                log.error("register producer {} error,can not found nameServer", beanName);
                continue;
            }
            producer.setNamesrvAddr(nameServer);
            producer.setInstanceName(an.instance());
            producer.setProducerGroup(an.group());
            try {
                producer.start();
            } catch (MQClientException e) {
                log.error("start producer {} error", beanName, e);
            }
            holder.put(messageProducer.getClass(), producer);
            log.info("register mq producer:{},nameserver:{} success", beanName, nameServer);
        }
    }

    @PreDestroy
    private void destory() {
        if (holder == null || holder.isEmpty()) {
            return;
        }
        for (Entry<Class<? extends MessageProducer>, DefaultMQProducer> entry : holder.entrySet()) {
            Class<? extends MessageProducer> cls = entry.getKey();
            DefaultMQProducer producer = entry.getValue();
            if (producer == null) {
                continue;
            }
            try {
                producer.shutdown();
            } catch (Exception e) {
                log.error("shutdown producer:{} error", cls.getName(), e);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static MQMessageStatus send(Class<? extends MessageProducer> cls, MQMessage msg,
            DelayLevel delay) {
        DefaultMQProducer producer = holder.get(cls);
        if (producer == null) {
            log.error("register {} producer error", cls.getName());
            return MQMessageStatus.FAIL;
        }
        Message message = new Message();
        message.setTopic(msg.getTopic());
        message.setTags(msg.getTag());
        message.setKeys(msg.getKey());
        message.setDelayTimeLevel(delay.ordinal());

        String data = msg.getData();
        message.setBody(data.getBytes(UTF_8));
        Map<String, String> userProperties = msg.getProperties();
        if (userProperties != null && !userProperties.isEmpty()) {
            for (Entry<String, String> uk : userProperties.entrySet()) {
                message.putUserProperty(uk.getKey(), uk.getValue());
            }
        }

        try {
            SendResult send = producer.send(message);
            msg.setMsgId(send.getMsgId());
            return MQMessageStatus.SUCCESS;
        } catch (MQClientException | MQBrokerException | RemotingException
                | InterruptedException e) {
            log.error("send msg error,client:{}", cls.getName(), e);
            return MQMessageStatus.FAIL;
        }
    }
}
