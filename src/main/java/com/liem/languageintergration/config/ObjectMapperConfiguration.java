package com.liem.languageintergration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper createObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModules(new JavaTimeModule());
        return mapper;
    }
}
