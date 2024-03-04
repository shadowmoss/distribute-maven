package com.ocean.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced // loadBalance组件提供的注解，用于提供调用时的负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
