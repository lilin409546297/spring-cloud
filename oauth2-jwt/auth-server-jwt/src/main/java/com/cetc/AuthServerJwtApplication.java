package com.cetc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthServerJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerJwtApplication.class, args);
    }
}
