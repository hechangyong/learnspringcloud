package com.hecy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: hecy
 * @Date: 2018/10/30 13:50
 * @Version 1.0
 */
public class AccessErrorFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(AccessErrorFilter.class);


    @Override
    /**
     * 过滤器类型， 决定在过滤器在请求的哪个生命周期里面会被执行
     * 返回 pre , 代表会在请求被路由之前执行，下面是源码解释。
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    public String filterType() {
        logger.info("into filterType error");
        return "error";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     * <p>
     * 过滤器的执行顺序， 当一个请求在一个阶段有多个过滤器的时候，会根据这个方法返回的值，决定执行顺序
     */
    @Override
    public int filterOrder() {
        logger.info("into filterOrder error");
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        logger.info("into shouldFilter error");

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("into run error");

        RequestContext ctx = RequestContext.getCurrentContext();

         ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(500);
        return null;

    }
}
