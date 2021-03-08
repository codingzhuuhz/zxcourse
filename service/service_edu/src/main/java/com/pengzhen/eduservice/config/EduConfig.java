package com.pengzhen.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.pengzhen.eduservice.mapper")
public class EduConfig {
    //配置逻辑删除的插件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector() ;
    }
    //配置分页查询的插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
