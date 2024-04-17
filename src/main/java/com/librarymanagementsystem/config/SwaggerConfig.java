package com.librarymanagementsystem.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi authApis(){
        String[] packagesToScan = {"com.librarymanagementsystem.security.auth"};
        return GroupedOpenApi.builder()
                .group("Auth Apis")
                .packagesToScan(packagesToScan)
                .addOperationCustomizer(operationCustomizer())
                .build();
    }

    @Bean
    public GroupedOpenApi usersApis() {
        String[] packagesToScan = {"com.librarymanagementsystem.ctrl"};
        return GroupedOpenApi.builder()
                .group("Management Apis")
                .packagesToScan(packagesToScan)
                .addOperationCustomizer(operationCustomizer())
                .build();
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (Operation operation , HandlerMethod handlerMethod) -> {
            Parameter headerParam = new Parameter()
                    .in(ParameterIn.HEADER.toString()).required(false)
                    .schema(new StringSchema()._default("default header")).name("app_header_token").description("app header token");
            operation.addParametersItem(headerParam);
            return operation;
        };
    }
}
