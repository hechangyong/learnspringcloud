package com.hecy.web;

import com.hecy.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient discoveryClient;
//    private CounterService counterService;

    @Autowired
    private Registration registration; // 服务注册

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        logger.info("helloserver hello");
        int sleeptime = new Random().nextInt(3000);
        try {
            Thread.sleep(sleeptime);
            logger.info("sleeptime: " + sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello world";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(@RequestParam String name) {
        logger.info("helloserver user----");
        return new User(name, 12);
    }

    @PostMapping(value = "/puser/{name}")
    public User getUser(@PathVariable("name") String name, @RequestBody Map<String, String> map) {
        logger.info("/puser name: " + name);
        logger.info("/puser map : " + map);


        return new User(name, 12);
    }

    @PostMapping(value = "/getuserwithheader/{name}")
    public User getUserwithheader(@PathVariable("name") String name, @RequestHeader Integer age) {
        logger.info("/puser name: {}" + name + " getUserwithheader : " + age);
        return new User(name, age);
    }


    @RequestMapping(value = "/fandAll", method = RequestMethod.GET)
    public List<User> fandAllUser(@RequestParam("ids") String ids) {
        logger.info("/fandAll ids : " + ids);
        List<User> result = new ArrayList<>();
        for (String id : ids.split(",")) {
            if (!id.isEmpty()) {
                result.add(new User(id, 12));
            }
        }
        return result;
    }

    @RequestMapping(value = "/cloud", method = RequestMethod.GET)
    public String getCount() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        StringBuffer sb = new StringBuffer();
        sb.append(" ");
        if (list != null && list.size() > 0) {
            for (ServiceInstance itm : list) {
                sb.append("ServiceId: " + itm.getServiceId() + " ");
            }
        }

        return sb.toString();
    }
}
