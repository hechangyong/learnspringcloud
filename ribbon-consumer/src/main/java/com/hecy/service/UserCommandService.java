package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * @Author: hecy
 * @Date: 2018/10/19 16:07
 * @Version 1.0
 */

public class UserCommandService extends HystrixCommand<User> {

    Logger logger = Logger.getLogger("UserCommandService");
    private RestTemplate restTemplate;

    private Long id;

    public UserCommandService(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.id = id;
        this.restTemplate = restTemplate;
    }


    @Override
    protected User run() throws Exception {
        logger.info("id: " + id);
         return restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
    }

    /**
     * run 方法发生错误， 线程阻塞后，自动调用 Fallback() 方法，实现服务降级
     * @return
     */
    @Override
    public User getFallback(){
        return new User();
    }

}
