package com.example.community.config;

import com.example.community.Interceptor.LoginInterceptor;
import okhttp3.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.security.auth.login.LoginContext;

/**
 * Copyright (C), 2018-2020
 * FileName: WebConfig
 * Author:   wdy
 * Date:     2020/4/10 9:51
 * Description: 有关拦截器的配置文件
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void configureClientInboundChannel(InterceptorRegistry registration) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registration.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
