package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 添加 @CacheResult 注解， 当服务被掉起来时， Hystrix会自动缓存结果。
     * cachekey : 1、如果么有指定特殊的 CacheKey ,则默认会使用此方法所有的参数拼接为key
     *            2、可以通过 如下${cacheKeyMethod} 指定 cacheKey
     *            3、还可以通过 如下${CacheKey} 指定 cacheKey. name 为User 属性字段名
     *优先级 2 > 3 > 1 即 使用了cacheKeyMethod后， CacheKey 配置的key 不会生效
     *
     * @return
     */
    @CacheResult(cacheKeyMethod = "helloConsumerCacheKey")
    @HystrixCommand(fallbackMethod = "defaultfallbackMethod",
            commandKey = "helloConsumer",
            groupKey = "helloConsumergroupkey",
            threadPoolKey = "helloConsumerThreadpoolKey")
    public String helloConsumer(@CacheKey("name") User user) {

        //todo 如果 是无参方法，且没有指定， cacheKey怎么生成。
        return restTemplate.getForObject("http://HELLOSERVER/hello", String.class);
    }

    /**
     * 添加 @CacheRemove 注解，清理缓存，通过命令key
     * commandKey 为必须
     * @return
     */
    @CacheRemove(commandKey = "helloConsumer")
    @HystrixCommand
    public String updateConsumer(@CacheKey("name") User user) {
        return restTemplate.getForObject("http://HELLOSERVER/hello", String.class);
    }


    public String helloConsumerCacheKey(User user){
        return "CacheKey";
    }

    public String defaultfallbackMethod(User user) {
        return "";
    }





}
