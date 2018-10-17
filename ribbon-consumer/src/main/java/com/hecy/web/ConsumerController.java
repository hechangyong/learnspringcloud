package com.hecy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon" , method = RequestMethod.GET)
    public String helloConsumer(){
        String s = restTemplate.getForObject("http://HELLOSERVER/hello", String.class);
        System.out.println("server body : " + s);
        return s;
    }

}
