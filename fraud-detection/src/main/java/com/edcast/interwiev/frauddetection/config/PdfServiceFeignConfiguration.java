package com.edcast.interwiev.frauddetection.config;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfServiceFeignConfiguration {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
