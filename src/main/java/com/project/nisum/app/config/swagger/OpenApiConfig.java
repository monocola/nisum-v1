package com.project.nisum.app.config.swagger;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig is a configuration class for the OpenAPI specification.
 * It uses the OpenAPI 3.0 specification for API documentation.
 */
@Configuration
public class OpenApiConfig {
    /**
     * Creates a new OpenAPI bean.
     * The OpenAPI object contains information about the API such as the title, version, contact, summary, terms of service, and description.
     * @return A new OpenAPI object.
     */
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Api nisum to test")
                        .version("1.0")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("jorgechilcon@gmail.com"))
                        .summary("User Authentication and Registration API")
                        .termsOfService("http://swagger.io/terms/")
                        .description("User Authentication and Registration API"));
    }


}