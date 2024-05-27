package com.platform.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 配置静态资源映射
     *
     * @param registry
     **/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射");
        //将路径中包含backend的请求映射到backend文件夹下
//这句话的意思是，只要请求的是/backend下面的文件都映射到Resources下面的backend文件夹下面,classpath:
        registry.addResourceHandler("/static/**").addResourceLocations("classpath*:/static/");
//上同
        //registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
}