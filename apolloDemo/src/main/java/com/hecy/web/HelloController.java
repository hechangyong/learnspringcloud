package com.hecy.web;

import com.hecy.springBootDemo.config.SampleRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    SampleRedisConfig sampleRedisConfig;

    @Autowired
    private Registration registration; // 服务注册

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        logger.info("sampleRedisConfig: ");
        return sampleRedisConfig.toString();
    }


}
