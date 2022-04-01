package com.edcast.interwiev.frauddetection.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
@Slf4j
public class JacksonConfiguration {

    private static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final LocalDateTimeSerializer LOCAL_DATE_TIME_SERIALIZER =
            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    public static final LocalDateTimeSerializer LOCAL_DATE_SERIALIZER =
            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT));

    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LOCAL_DATE_TIME_SERIALIZER);
        module.addSerializer(LOCAL_DATE_SERIALIZER);
        return module;
    }
}
