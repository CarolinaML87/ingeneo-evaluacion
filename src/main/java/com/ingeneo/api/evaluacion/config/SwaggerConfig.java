/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@Component
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(SecurityContext.builder().securityReferences(defaultAuth()).build()))
                .securitySchemes(Arrays.asList(new ApiKey("JWT", "Authorization", "header")))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ingeneo.api.evaluacion.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getInfo());
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
    }
    
    private ApiInfo getInfo() {
        return new ApiInfo(
                "Ingeneo API",
                "Evaluacion Tecnica",
                "1.0",
                "http://",
                "Carolina Mercado",
                "LICENSE",
                "URL LICENSE"
        );
    }
}
