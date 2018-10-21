package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCollapser;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.logging.Logger;

/**
 * @Author: hecy
 * @Date: 2018/10/19 16:07
 * @Version 1.0
 */

public class UserObservableCommandService extends HystrixObservableCommand<User> {

    Logger logger = Logger.getLogger("UserObservableCommandService" +
            "");
    private RestTemplate restTemplate;

    private Long id;

    public UserObservableCommandService(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.id = id;
        this.restTemplate = restTemplate;
    }


     protected User run() throws Exception {
        logger.info("id: " + id);
         return restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
    }

    @Override
    protected Observable<User> construct() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                if(!subscriber.isUnsubscribed()){
                    User user = restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }
        });
    }


    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY )
    public Observable<User> getUserById(){
        return  Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try{
                    if(!subscriber.isUnsubscribed()){
                        User user = restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                }catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }
}
