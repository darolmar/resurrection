package com.edorasware.homework1.configuration;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket expensesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(expensesApiInfo())
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.edorasware.homework1.controller"))
                .build()
                .useDefaultResponseMessages(false);
    }
 
    private ApiInfo expensesApiInfo() {
        return new ApiInfoBuilder()
                .title("ExpenseService REST API")
                .description("ExpenseService REST API documentation")
                .termsOfServiceUrl("http://en.wikipedia.org/wiki/Terms_of_service")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("2.0")
                .build();
    }
}