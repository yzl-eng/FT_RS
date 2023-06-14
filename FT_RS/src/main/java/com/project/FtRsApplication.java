package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.project")
@MapperScan("com.project.mapper")
public class FtRsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtRsApplication.class, args);
    }

}
