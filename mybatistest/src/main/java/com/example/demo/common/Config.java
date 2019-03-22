package com.example.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zyb on 2018/9/19.
 */
@Configuration
public class Config {

    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
