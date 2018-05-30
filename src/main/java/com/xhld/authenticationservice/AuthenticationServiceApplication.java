package com.xhld.authenticationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import com.lzh.common.annotation.MyBatisRepository;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.lzh.common.util,com.xhld.authenticationservice")
@MapperScan(basePackages = "com.xhld.authenticationservice.persistence", annotationClass = MyBatisRepository.class)
public class AuthenticationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
    
}