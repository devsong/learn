package com.gzs.learn.rocketmq.register;

import static com.alibaba.rocketmq.common.consumer.ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET;

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

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.Message;
import com.gzs.learn.rocketmq.annotation.RocketMQConsumer;
import com.gzs.learn.rocketmq.common.MQMessage;
import com.gzs.learn.rocketmq.common.MQMessageStatus;
import com.gzs.learn.rocketmq.inf.MessageConsumer;

import lombok.extern.slf4j.Slf4j;

/**
 * 注册各类消息消费者
 *
 * @author guanzhisong
 * @date 2017年11月27日
 */
@Slf4j
@Component
public class MessageConsumerRegister implements ApplicationContextAware {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    private static ApplicationContext ctx;
    private ConcurrentHashMap<String, DefaultMQPushConsumer> holder = new ConcurrentHashMap<>();

    @Value("${com.gzs.learn.rocketmq.nameserver:''}")
    private String defaultNameServer;

    @PostConstruct
    private void initConsumer() {
        Map<String, MessageConsumer> beanMap = ctx.getBeansOfType(MessageConsumer.class);
        for (Entry<String, MessageConsumer> entry : beanMap.entrySet()) {
            String beanName = entry.getKey();
            MessageConsumer messageConsumer = entry.getValue();

            RocketMQConsumer an = messageConsumer.getClass().getAnnotation(RocketMQConsumer.class);
            if (an == null) {
                continue;
            }
            DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(an.group());
            // 以实现类配置的nameServer为第一优先级
            String nameServer = messageConsumer.getNameServer();
            if (StringUtils.isBlank(nameServer)) {
                // 配置文件环境变量为第二优先级
                nameServer = defaultNameServer;
            }

            if (StringUtils.isBlank(nameServer)) {
                log.error("register producer {} error,can not found nameServer", beanName);
                continue;
            }
            pushConsumer.setNamesrvAddr(nameServer);
            pushConsumer.setInstanceName(an.instance());
            try {
                pushConsumer.subscribe(an.topic(), an.tag());
                // 程序启动时，从上次消费的断点继续消费
                pushConsumer.setConsumeFromWhere(CONSUME_FROM_FIRST_OFFSET);
                MessageListenerConcurrently listener = this.createConsumer(messageConsumer);
                pushConsumer.registerMessageListener(listener);
                pushConsumer.start();
                holder.put(beanName, pushConsumer);
                log.info("register mq consumer:{},nameserver:{} success", beanName, nameServer);
            } catch (Exception e) {
                log.error("init consumer failed", e);
            }
        }
    }

    private MessageListenerConcurrently createConsumer(MessageConsumer messageConsumer) {
        MessageListenerConcurrently listener = (list, Context) -> {
            Message msg = list.get(0);
            MQMessage message = new MQMessage(msg.getKeys(), msg.getTopic(), msg.getTags(),
                    new String(msg.getBody(), UTF8));
            MQMessageStatus consumeStatus = null;
            try {
                consumeStatus = messageConsumer.consume(message);
            } catch (Exception e) {
                log.error("consumed failed, try it later! message is: {} \n", msg.toString(), e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.values()[consumeStatus.ordinal()];
        };
        return listener;
    }

    @PreDestroy
    public void destory() {
        if (holder == null || holder.isEmpty()) {
            return;
        }
        for (Entry<String, DefaultMQPushConsumer> entry : holder.entrySet()) {
            String beanName = entry.getKey();
            DefaultMQPushConsumer consumer = entry.getValue();
            if (consumer == null) {
                continue;
            }
            try {
                consumer.shutdown();
            } catch (Exception e) {
                log.error("shutdown consumer:{} error", beanName, e);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
