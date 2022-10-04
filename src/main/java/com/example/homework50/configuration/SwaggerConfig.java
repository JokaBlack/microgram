package com.example.homework50.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!test!!!!!!!!!!!!!!!!!!!!!");
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Spring Data REST API")
                                .version("1.0.0")
                );
    }

}
