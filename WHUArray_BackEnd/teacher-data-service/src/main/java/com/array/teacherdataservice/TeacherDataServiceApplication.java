package com.array.teacherdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TeacherDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherDataServiceApplication.class, args);
    }

}
