package com.project.filter;

import com.alibaba.fastjson.JSONObject;
import com.project.entity.Result;
import com.project.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class CheckLoginFilter implements javax.servlet.Filter {

    @Override//初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;

        String url=req.getRequestURI().toString();
        log.info("请求的url",url);

        if(url.contains("login")){
            log.info("登录操作，放行...");
            chain.doFilter(request,response);
            return;
        }

        String jwt=req.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空");
            Result error= Result.error("NOT_LOGIN");
            String noLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return;
        }

        try {
            JwtUtil.verify(jwt);
        }catch (Exception e){
            log.info("解析令牌失败");
            Result error= Result.error("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(notLogin);
            return;
        }
        //放行
        log.info("令牌合法");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        javax.servlet.Filter.super.destroy();
    }
}
