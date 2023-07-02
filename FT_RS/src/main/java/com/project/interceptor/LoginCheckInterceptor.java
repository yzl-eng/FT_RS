package com.project.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.project.entity.Result;
import com.project.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {


        String url=req.getRequestURI().toString();
        log.info("请求的url",url);


        if(url.contains("login")){
            log.info("登录操作，放行...");
            return true;
        }
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(req.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }

        String jwt=req.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空");
            Result error= Result.error("NOT_LOGIN");
            String noLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return false;
        }
        try {
            JwtUtil.verify(jwt);
        }catch (Exception e){
            log.info("解析令牌失败");
            Result error= Result.error("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(notLogin);
            return false;
        }
        //放行
        log.info("令牌合法");
        return true;
    }
}
