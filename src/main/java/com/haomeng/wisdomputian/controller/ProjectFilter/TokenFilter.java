package com.haomeng.wisdomputian.controller.ProjectFilter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器实现过滤需要登陆才能访问的服务过滤
 */
public class TokenFilter implements Filter {
    private Logger log = Logger.getLogger(TokenFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // do something 处理request 或response
        // doFilter()方法中的servletRequest参数的类型是ServletRequest，需要转换为HttpServletRequest类型方便调用某些方法
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取ip
        String ip = request.getRemoteAddr();
        log.info("ip:" + ip);
        String url = request.getRequestURL().toString();
        log.info("url:" + url);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
