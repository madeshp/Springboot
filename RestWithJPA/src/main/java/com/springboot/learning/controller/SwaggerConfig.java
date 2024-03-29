package com.springboot.learning.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile(value = "default,dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /* @Bean
     public Docket postsApi() {
         return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                 .apiInfo(apiInfo()).select().paths(postPaths()).build();
     }
 */
    @Bean

    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.springboot.learning"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

/*
    private Predicate<String> postPaths() {
        return or(regex("/.*"), regex("/.*"));
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee API")
                .description("Employee API reference for developers")
                .termsOfServiceUrl("http://employee.com")
                .contact("Springboot@gmail.com").license("spring boot  License")
                .licenseUrl("Springboot@gmail.com").version("1.0").build();
    }

}