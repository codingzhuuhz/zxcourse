package com.pengzhen.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//开启定时任务
@EnableDiscoveryClient //注册nacos
@EnableFeignClients
@MapperScan(basePackages = {"com.pengzhen.statistic.mapper"})
@ComponentScan(basePackages = {"com.pengzhen"})
@SpringBootApplication
public class StatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class,args);
    }
}
