package com.fullstack.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    
    @Bean
    public feign.RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
        };
    }
    
    @Bean
    public feign.Retryer retryer() {
        return new feign.Retryer.Default(1000, 2000, 3);
    }
}