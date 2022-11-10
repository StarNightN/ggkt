package com.atguigu.ggkt.vod.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
// * <p>
// * 实现 WebMvcConfigurer接口，重写addCorsMappings，实现跨域解决
// * </p>
// */
//@Configuration
//public class CrossConfiguration implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*") //跨域
//				.allowedHeaders("*")
//				.allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
//				.allowCredentials(true) //是否允许携带cookie
//				.maxAge(3600);
//	}
//
//}
