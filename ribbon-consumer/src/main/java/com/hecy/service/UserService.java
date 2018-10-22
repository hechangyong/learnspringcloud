package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: hecy
 * @Date: 2018/10/19 16:37
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "defaultfallbackMethod")
    public User getUser() {
        return restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
    }

    @HystrixCommand(fallbackMethod = "defaultfallbackMethod")
    public List<User> fandAllUser() {
        return restTemplate.getForObject("http://HELLOSERVER/user?ids={1}", List.class, "1,2,3,4");
    }



    @HystrixCommand(fallbackMethod = "defaultfallbackMethod1", ignoreExceptions = Exception.class)
    public Future<User> asyncGetUser() {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
            }
        };
    }




    public User defaultfallbackMethod() {
        return null;
    }
    public User defaultfallbackMethod1() {
        return null;
    }


}
