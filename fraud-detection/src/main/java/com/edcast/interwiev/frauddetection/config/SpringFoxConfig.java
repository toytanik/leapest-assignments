package com.edcast.interwiev.frauddetection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }


    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Fraud Detection service apis",
                "This application provides api for building Edcast fraud detection application.",
                "1.0.0",
                "TERMS OF SERVICE URL",
                new Contact("Tuncay Türk", "https://www.linkedin.com/in/tuncayturk/", "tuncayturk.cse@gmail.com"),
                "",
                "",
                Collections.emptyList()
        );
    }

}
