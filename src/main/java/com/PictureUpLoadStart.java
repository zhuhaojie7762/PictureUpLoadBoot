package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ImportResource(value={"classpath:spring/common-dal.xml"})
@ServletComponentScan("com.platform.servlet")
@MapperScan(value = "com.platform.dao")
public class PictureUpLoadStart {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PictureUpLoadStart.class);
        springApplication.run(args);
    }
}
