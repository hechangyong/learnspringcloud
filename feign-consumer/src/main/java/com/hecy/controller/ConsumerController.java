package com.hecy.controller;

import com.hecy.f_server.HelloService;
import com.hecy.interfaceapi.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hecy
 * @Date: 2018/10/25 15:19
 * @Version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-getuser", method = RequestMethod.GET)
    public String getUser() {
        Map<String, String> s = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        s.put("name", "sasda");
        sb.append(helloService.getUser("hecy", s)).append("\n");
        sb.append(helloService.fandAllUser("hechangyong")).append("\n");
        sb.append(helloService.getUserwithheader("getUserwithheader", 100)).append("\n");
        return sb.toString();
    }



}
