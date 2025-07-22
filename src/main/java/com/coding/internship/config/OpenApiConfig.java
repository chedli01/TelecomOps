package com.coding.internship.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(

                description = "OpenApi documentation for Spring Boot App",
                title = "OpenApi specification - TelecomOp",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),

        }
)

public class OpenApiConfig {
}
