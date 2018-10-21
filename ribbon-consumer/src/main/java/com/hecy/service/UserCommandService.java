package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * @Author: hecy
 * @Date: 2018/10/19 16:07
 * @Version 1.0
 * 此单元类 中演示了 熔断器中 命令请求
 *  熔断降级
 *  请求缓存
 *  请求缓存清理
 *  以上 具体注解配置 参考 ${@link HelloService}
 */

public class UserCommandService extends HystrixCommand<User> {

    Logger logger = Logger.getLogger("UserCommandService");

    private RestTemplate restTemplate;

    private Long id;

    private static final HystrixCommandKey COMMAND_KEY_FOR_GETUSER = HystrixCommandKey.Factory
            .asKey("HystrixCommandKey");

     private static String CACHE_KEY_FOR_GET_USER = null;

    public UserCommandService(RestTemplate restTemplate, Long id) {
        /**
         * 通过设置命令组名， Hystrix 会根据组名来组织和统计命令的告警 仪表盘等信息
         * Hystrix 会让相同的命令组使用同一个线程。
         * 还可以通过 @andThreadPoolKey 来设置线程池
         * 具体的 注解使用方法相见 {@link HelloService.helloConsume() } 的
         */
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("CommandGroupKey"))
                .andCommandKey(COMMAND_KEY_FOR_GETUSER)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
        this.id = id;
        CACHE_KEY_FOR_GET_USER = String.valueOf(id);
        this.restTemplate = restTemplate;
    }


    @Override
    protected User run() throws Exception {
        logger.info("id: " + id);
        return restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
    }

    /**
     * run 方法发生错误，或者 线程阻塞后，自动调用 Fallback() 方法，实现服务降级
     *
     * @return
     */
    @Override
    public User getFallback() {
        return new User();
    }


    /**
     * 开启请求缓存功能
     * 以下源码解释
     *  **
     *      * Key to be used for request caching.
     *      * <p>
     *      * By default this returns null which means "do not cache".
     *      * <p>
     *      * To enable caching override this method and return a string key uniquely representing the state of a command instance.
     *      * <p>
     *      * If multiple command instances in the same request scope match keys then only the first will be executed and all others returned from cache.
     *      *
     *      * @return cacheKey
     *      *
     */
    @Override
    public String getCacheKey(){
        logger.info("获取缓存Key");
        return  CACHE_KEY_FOR_GET_USER;
    }


    /**
     * 刷新缓存 ，根据缓存的key 进行清理
     */
    public void flushCache(){
        HystrixRequestCache
                .getInstance(COMMAND_KEY_FOR_GETUSER, HystrixConcurrencyStrategyDefault.getInstance())
                .clear(CACHE_KEY_FOR_GET_USER);

    }


}
