package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ImportResource(value={"classpath:spring/common-dal.xml"})
@MapperScan(value = "com.platform.dao") //A component required a bean named 'orderOriginDAO' that could not be found
public class PictureUpLoadStart {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PictureUpLoadStart.class);
        springApplication.run(args);
    }
}
