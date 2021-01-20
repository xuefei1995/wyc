package com.xuefei.config;

import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //告诉调用方要调哪台主机策略，开启后，会在注册中心拉到本地的map中，根据项目名称作为key找到对应的map，轮询里面的实例
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    //设置feign的日志级别
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
