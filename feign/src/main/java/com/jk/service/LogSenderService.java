package com.jk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogSenderService implements RabbitTemplate.ReturnCallback {

    private static final Logger log = LoggerFactory.getLogger(LogSenderService.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("AckSender returnedMessage " + message.toString() + " === " + i + " === " + s1 + " === " + s2);
        log.info("AckSender returnedMessage " + message.toString() + " === " + i + " === " + s1 + " === " + s2);
    }

    public void send(String logJson) {
        //设置返回回调
        rabbitTemplate.setReturnCallback(this);
        //设置确认回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack) {
                System.out.println("消息发送成功");
                log.info("消息发送成功");
            } else {
                System.out.println("消息发送失败, 原因：" + cause + correlationData.toString());
                log.error("消息发送失败, 原因：" + cause + correlationData.toString());
            }
        });
        rabbitTemplate.convertAndSend("log-queue", logJson);
    }
}
