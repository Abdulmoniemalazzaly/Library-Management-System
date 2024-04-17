package com.librarymanagementsystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer oAuth Token";
    private static final String JWT = "JWT";
    private static final String BEARER = "Bearer";

    @Bean
    public OpenAPI openAPI(@Value("${openApi.application-description}") String appDescription ,
                           @Value("${openApi.application-version}") String appVersion ){
        return new OpenAPI()
                .info(info(appVersion ,appDescription))
                .addSecurityItem(
                        new SecurityRequirement().addList(SECURITY_SCHEME_NAME , Arrays.asList("read" , "write"))
                )
                .components(
                        new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme())
                );
    }

    private SecurityScheme securityScheme(){
        return new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER)
                .bearerFormat(JWT);
    }

    private Info info(String appVersion , String appDescription){
        return new Info().title("Library Management System")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("http://swagger.io/terms/");
    }

}
