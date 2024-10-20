package com.example.Hotel.managment.system.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;


@Configuration
public class SwaggerConfig {
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(HTTP)
                .bearerFormat("JWT")
                .scheme("BEARER");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("Calendar control SERVICE")
                        .description("Hotel Management API with all endpoints and permissions")
                        .version("0.0.1"));
    }
}