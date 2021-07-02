package com.bamboo.board.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bamboo.board.interceptor.LoginIntercepter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    /*
     * 로그인 인증 Interceptor 설정
     * */
    @Autowired
    LoginIntercepter loginIntercepter;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/**/*.do");
    }
}