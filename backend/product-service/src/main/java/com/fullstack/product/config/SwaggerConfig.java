package com.fullstack.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI productServiceOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Product Service API")
                .description("Microservice for product management following JSON API standards")
                .version("1.0.0")
                .contact(new Contact()
                    .name("FullStack Team")
                    .email("team@fullstack.com")));
    }
}