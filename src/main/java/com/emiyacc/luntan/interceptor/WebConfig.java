package com.emiyacc.luntan.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
// 加上下面的 css 样式也会被拦截
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 表示添加检测的地址
        // excludePathPatterns 忽略检测的地址
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/");
    }
}