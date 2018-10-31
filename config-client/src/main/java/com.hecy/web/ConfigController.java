package com.hecy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hecy
 * @Date: 2018/10/31 17:27
 * @Version 1.0
 */
@Configuration
@EnableAutoConfiguration
@RestController
public class ConfigController {

    @Autowired
    private Environment env;

//    @Value("${from}")
//    String from;

    @RequestMapping("/e")
    public String env() {
        String envForm = env.getProperty("from","dddd");

        return " envForm:  " + envForm;

    }
}

