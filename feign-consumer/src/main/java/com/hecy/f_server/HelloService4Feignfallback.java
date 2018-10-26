package com.hecy.f_server;

import com.hecy.bean.User;
import com.hecy.interfaceapi.IHelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author: hecy
 * @Date: 2018/10/25 17:01
 * @Version 1.0
 * 这个类为helloservice 服务降级 返回
 */
@Component
public class HelloService4Feignfallback implements HelloService {
    private final Logger logger = Logger.getLogger("HelloService4Feign");

    @Override
    public String hello() {
        logger.info("【fallback】  helloserver hello fallback");

        return "Hello world";
    }

    @Override
    public User getUser(@PathVariable("name")  String name, Map<String, String> map) {
        logger.info("【fallback】 HelloService4Feign  getUser name: " + name);
        return new User(name, 100);
    }

    @Override
    public User getUserwithheader(@PathVariable("name") String name, @RequestHeader("age") Integer age) {
        logger.info("【fallback】  getUserwithheader name: " + name);
        return new User(name, 100);
    }

    @Override
    public List<User> fandAllUser(@RequestParam("ids") String ids) {
        logger.info("【fallback】  fandAllUser    ids: " + ids);
        List<User> list = new ArrayList<>();
        list.add(new User("hecy", 100));
        return list;
    }
}
