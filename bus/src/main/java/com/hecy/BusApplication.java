package com.hecy;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
 public class BusApplication {


    public static void main(String args[]) {
        new SpringApplicationBuilder(BusApplication.class).web(true).run(args);
    }


}

