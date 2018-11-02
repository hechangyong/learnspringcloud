package com.hecy.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @Author: hecy
 * @Date: 2018/11/2 14:13
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void process(String hello) {
        logger.info("receive message: " + hello);
    }


}
