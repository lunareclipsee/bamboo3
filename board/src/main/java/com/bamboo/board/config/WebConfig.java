//package com.bamboo.board.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.bamboo.board.interceptor.LoginIntercepter;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		log.info("인터셉터온ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ");
//        LoginIntercepter loginIntercepter = new LoginIntercepter();
//		registry.addInterceptor(loginIntercepter).addPathPatterns("/**/*.do").excludePathPatterns();
//	}
//}