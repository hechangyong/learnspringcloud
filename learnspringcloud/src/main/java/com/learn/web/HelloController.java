package com.learn.web;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cloud.client.ServiceInstance;
 import org.springframework.cloud.client.discovery.DiscoveryClient;
 import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;
//    private CounterService counterService;

    @Autowired
    private Registration registration; // 服务注册

    @RequestMapping("/hello")
    public String index(){
        return "Hello world";
    }

    @RequestMapping("cloud")
    public void getCount(){
         List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                     

            }
        }

    }
}
