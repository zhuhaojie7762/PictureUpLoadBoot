package com;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@ImportResource(value={"classpath:spring/common-dal.xml"})
//@MapperScan(value = "com.platform.dao")
public class PictureUpLoadStart {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PictureUpLoadStart.class);
        springApplication.run(args);
    }
}
