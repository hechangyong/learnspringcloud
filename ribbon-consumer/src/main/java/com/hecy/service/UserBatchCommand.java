package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: hecy
 * @Date: 2018/10/22 10:58
 * @Version 1.0
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    @Autowired
    UserService userService;

    protected UserBatchCommand() {
         super(Setter.withGroupKey( HystrixCommandGroupKey.Factory.asKey("UserBatchCommandGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserBatchCommandKey"))
                 .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserBatchThreadPoolKey"))
         );
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.fandAllUser();
    }
}
