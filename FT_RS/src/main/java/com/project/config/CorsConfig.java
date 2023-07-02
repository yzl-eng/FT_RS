package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//跨域请求处理

/**
 * 全局跨域解决
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOriginPatterns("*").
//                allowedOrigins("*"). //允许跨域的域名，可以用*表示允许任何域名使用
        allowedMethods("*"). //允许任何方法（post、get等）
                allowedHeaders("*"). //允许任何请求头
                allowCredentials(true) //带上cookie信息
                .exposedHeaders("Server","Content-Length", "Access-Token", "Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Token");
    }
}