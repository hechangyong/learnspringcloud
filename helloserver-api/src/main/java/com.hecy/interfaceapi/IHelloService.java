package com.hecy.interfaceapi;

import com.hecy.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/feign")
public interface IHelloService {

    @RequestMapping("/hello")
    String hello();

    @PostMapping(value = "/puser/{name}")
    User getUser(@PathVariable("name") String name, @RequestBody Map<String, String> map);

    @PostMapping(value = "/getuserwithheader/{name}")
    User getUserwithheader(@PathVariable("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/fandAll", method = RequestMethod.GET)
    List<User> fandAllUser(@RequestParam("ids") String ids);

}