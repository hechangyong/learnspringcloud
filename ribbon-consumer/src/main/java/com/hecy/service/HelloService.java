package com.hecy.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultfallbackMethod")
    public String helloConsumer() {
        return restTemplate.getForObject("http://HELLOSERVER/hello", String.class);
    }

    public String defaultfallbackMethod() {
        return "error";
    }

}
