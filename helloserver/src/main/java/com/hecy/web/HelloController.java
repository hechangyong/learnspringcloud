package com.hecy.web;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cloud.client.ServiceInstance;
 import org.springframework.cloud.client.discovery.DiscoveryClient;
 import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RestController;

import java.util.List;
  import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient discoveryClient;
//    private CounterService counterService;

    @Autowired
    private Registration registration; // 服务注册

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String index(){
        logger.info("helloserver hello");
        return "Hello world";
    }

    @RequestMapping(value="/cloud", method=RequestMethod.GET)
    public String getCount(){
         List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
         StringBuffer sb = new StringBuffer();
         sb.append(" ");
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                sb.append("ServiceId: "+itm.getServiceId()+" ");
            }
        }

        return sb.toString();
    }
}
