package com.pengzhen.msc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.pengzhen")
@ComponentScan(basePackages = {"com.pengzhen"})
public class MscApplication {
    public static void main(String[] args) {
        SpringApplication.run(MscApplication.class,args);
    }
}
