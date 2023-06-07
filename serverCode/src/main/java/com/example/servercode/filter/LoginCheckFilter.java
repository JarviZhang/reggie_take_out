package com.example.servercode.filter;


import com.alibaba.fastjson.JSON;
import com.example.servercode.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/*
* 检查用户是否登陆
* */
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /*
    * 拦截没有登陆的用户对其他页面的请求
    * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取本次请求的uri
        String requestURI = request.getRequestURI();
        log.info("拦截到请求:{}",requestURI);
        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //判断本次请求是否需要处理
        boolean check = check(urls,requestURI);
        //如果不需要处理,直接放行
        if (check){
            log.info("本次请求不需要处理:{}",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //判断登陆状态,如果已经登陆则直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登陆，用户的id为:{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }
        //如果未登录则返回登陆页面，通过输出流的方式想客户端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("用户未登录：{}",requestURI);
        return;
    }
    //判断本次请求是否放行(true:不需要处理  false:需要处理)
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
