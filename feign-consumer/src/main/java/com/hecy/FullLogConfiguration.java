package com.hecy;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hecy
 * @Date: 2018/10/26 16:37
 * @Version 1.0
 * 在具体的Feign 客户端 指定此配置类以实现是否要调整不同的日志级别
 */
@Configuration
public class FullLogConfiguration  {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
