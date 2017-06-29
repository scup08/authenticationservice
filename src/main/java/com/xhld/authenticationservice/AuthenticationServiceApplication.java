package com.xhld.authenticationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.xhld.authenticationservice.mapper")
public class AuthenticationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
}