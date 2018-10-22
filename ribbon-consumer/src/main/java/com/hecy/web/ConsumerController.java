package com.hecy.web;

import com.hecy.bean.User;
import com.hecy.service.HelloService;
import com.hecy.service.UserCommandService;
import com.hecy.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@RestController
public class ConsumerController {

    Logger logger = Logger.getLogger("ConsumerController");


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/ribbon", method = RequestMethod.GET)
    public String helloConsumer() {
        HystrixRequestContext.initializeContext();
//        String s = restTemplate.getForObject("http://HELLOSERVER/hello", String.class);
        String s = helloService.helloConsumer(new User("helloConsumer", 12));
        System.out.println("server body : " + s);
        return s;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        User user = restTemplate.getForObject("http://HELLOSERVER/user?name={1}", User.class, "hecy");
        System.out.println("server body : " + user);

        Map<String, String> param = new HashMap<>();
        param.put("name", "hecymap");
        User user2 = restTemplate.getForObject("http://HELLOSERVER/user?name={name}", User.class, param);
        System.out.println("server body : " + user2);

        return user2;
    }


    @RequestMapping(value = "/puser", method = RequestMethod.GET)
    public User getPUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", "e348bc22-5efa-4299-9142-529f07a18ac9");
        headers.add("Content-Type", "application/json; charset=utf8");

        Map<String, String> postParameters = new HashMap<>();
        postParameters.put("owner", "11");
        postParameters.put("subdomain", "aoa");
        postParameters.put("comment", "");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(postParameters, headers);

        Map<String, String> param = new HashMap<>();
        param.put("name", "hecymap");
        ResponseEntity<User> user2 = restTemplate.postForEntity("http://HELLOSERVER/puser/{1}", requestEntity, User.class, "hecy");
        System.out.println("server body : " + user2);
        User user3 = restTemplate.postForObject("http://HELLOSERVER/puser/{1}", requestEntity, User.class, "hecy");
        System.out.println("user3: " + user3);
        return user2.getBody();
    }


    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUserByIdWithHystrix() throws ExecutionException, InterruptedException {
        User u = new UserCommandService(restTemplate, 10L).execute();

        Future<User> fu = new UserCommandService( restTemplate, 10L).queue();


        Observable<User> observe = new UserCommandService(restTemplate, 10L).observe();

        User u2 = fu.get();
        logger.info("user2: " + u2);
        return u;
    }

    @RequestMapping(value = "/getUser1", method = RequestMethod.GET)
    public User getUserWithHystrix() throws ExecutionException, InterruptedException {
        User u2 = userService.getUser();
        Future<User> fu = userService.asyncGetUser();
        logger.info("user2: " + u2);
        logger.info("fu.getu: " + fu.get());
        return u2;
    }


}
