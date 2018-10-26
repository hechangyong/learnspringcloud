package com.hecy.web;

import com.hecy.bean.User;
import com.hecy.interfaceapi.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @Author: hecy
 * @Date: 2018/10/25 17:01
 * @Version 1.0
 */
@RestController
public class HelloService4Feign implements IHelloService {
    private final Logger logger = Logger.getLogger("HelloService4Feign");
    @Autowired
    private DiscoveryClient discoveryClient;
//    private CounterService counterService;

    @Autowired
    private Registration registration; // 服务注册

    @Override
    public String hello() {
        logger.info("helloserver hello");

        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        StringBuffer sb = new StringBuffer();
        sb.append(" ");
        if (list != null && list.size() > 0) {
            for (ServiceInstance itm : list) {
                sb.append("ServiceId: " + itm.getServiceId() + "  itm.getHost(): "+ itm.getHost() + " \n" );
            }
        }
        logger.info("sb: " + sb.toString());

        int sleeptime = new Random().nextInt(3000);
        try {
            Thread.sleep(sleeptime);
            logger.info("sleeptime: " + sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello world";
    }

    @Override
    public User getUser(@PathVariable("name")  String name, Map<String, String> map) {
        logger.info("HelloService4Feign  getUser name: " + name);
        return new User(name, 100);
    }

    @Override
    public User getUserwithheader(@PathVariable("name") String name, @RequestHeader("age") Integer age) {
        logger.info("getUserwithheader name: " + name);
        return new User(name, 100);
    }

    @Override
    public List<User> fandAllUser(@RequestParam("ids") String ids) {
        logger.info("fandAllUser    ids: " + ids);
        List<User> list = new ArrayList<>();
        list.add(new User("hecy", 100));
        return list;
    }
}
