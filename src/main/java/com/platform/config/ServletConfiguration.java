//package com.platform.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class ServletConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        // 将请求映射到自定义的Servlet
//        registry.addServletMapping("/toUp", "com.platform.servlet.FileUpLoad");
//        //registry.addViewController("/toUp");
//    }
//}