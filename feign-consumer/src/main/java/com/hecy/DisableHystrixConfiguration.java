package com.hecy;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @Author: hecy
 * @Date: 2018/10/26 15:42
 * @Version 1.0
 */
public class DisableHystrixConfiguration {


    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

}
