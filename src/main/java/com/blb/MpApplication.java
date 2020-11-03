package com.blb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.blb.mapper")
@SpringBootApplication
public class MpApplication {
    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class,args);
    }
}
