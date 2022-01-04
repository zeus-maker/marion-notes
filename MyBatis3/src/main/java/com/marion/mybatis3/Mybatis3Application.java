package com.marion.mybatis3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.marion.mybatis3.dao")
@SpringBootApplication
public class Mybatis3Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatis3Application.class, args);
    }

}
