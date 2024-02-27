package com.management.pizzaria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
       return new OpenAPI()
               .info(new Info()
                       .title("RESTful API with SpringBoot 3")
                       .version("v1")
                       .description("API REST responsible for managing a pizzeria.")
                       .termsOfService("")
                       .license(new License().name("Apache 2.0")
                               .url("")));
    }
}
