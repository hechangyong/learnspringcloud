package com.hecy.service;

import com.hecy.bean.User;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.List;

/**
 * @Author: hecy
 * @Date: 2018/10/22 13:42
 * @Version 1.0
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User, Long> {
    @Override
    public Long getRequestArgument() {
        return null;
    }

    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collapsedRequests) {
        return null;
    }

    @Override
    protected void mapResponseToRequests(List<User> batchResponse, Collection<CollapsedRequest<User, Long>> collapsedRequests) {

    }
}
