package com.hecy.rabbitmq;

import com.hecy.utils.LogUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: hecy
 * @Date: 2018/11/2 13:59
 * @Version 1.0
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    LogUtils logUtils;

    public void send() {
        String context = "hello rabbitmq : " + new Date();
        logUtils.logInfo(Sender.class, context);
        this.amqpTemplate.convertAndSend("hello", context);
    }


}
