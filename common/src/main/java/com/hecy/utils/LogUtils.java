package com.hecy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: hecy
 * @Date: 2018/11/2 14:19
 * @Version 1.0
 */
@Component
public class LogUtils {





    public void logInfo(Class<?> clazz, String message){
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(message);
    }



}
