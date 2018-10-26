package com.hecy.f_server;

import com.hecy.DisableHystrixConfiguration;
import com.hecy.FullLogConfiguration;
import com.hecy.bean.User;
import com.hecy.interfaceapi.IHelloService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient( name = "HELLOSERVER" , fallback = HelloService4Feignfallback.class, configuration = FullLogConfiguration.class)
public interface HelloService   {
    @RequestMapping("/hello")
    String hello();

    @PostMapping(value = "/puser/{name}")
    User getUser(@PathVariable("name") String name, @RequestBody Map<String, String> map);

    @PostMapping(value = "/getuserwithheader/{name}")
    User getUserwithheader(@PathVariable("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/fandAll", method = RequestMethod.GET)
    List<User> fandAllUser(@RequestParam("ids") String ids);

}
