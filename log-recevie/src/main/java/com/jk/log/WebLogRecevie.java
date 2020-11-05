package com.jk.log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.jk.entity.LogBean;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@RabbitListener(queues = "log-queue")
public class WebLogRecevie {

    private static final Logger logger = LoggerFactory.getLogger(WebLogRecevie.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @RabbitHandler
    public void logRecevie(String logMes, Channel channel, Message message) {
        try {
            System.out.println(logMes);
            logger.info(logMes);
            LogBean log = JSONObject.parseObject(logMes, LogBean.class);
            System.out.println(log);
            logger.info(log.toString());
            // 存储mongodb
            mongoTemplate.save(log, "logBean");

            System.out.println(message.getMessageProperties().getDeliveryTag());
            logger.info("消费消息第" + message.getMessageProperties().getDeliveryTag() + "条");

            //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (IOException e){
            System.out.println("消费消息失败");
            logger.error("消费消息失败, cause: " + e.getCause());
            e.printStackTrace();
        }

    }



}
