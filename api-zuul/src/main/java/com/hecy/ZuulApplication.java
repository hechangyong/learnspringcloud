package com.hecy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {


    public static void main(String args[]){
//        SpringApplication.run(ZuulApplication.class, args);
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }

}
