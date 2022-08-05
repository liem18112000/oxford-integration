package com.liem.languageintergration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Object mapper configuration.
 */
@Configuration
public class ObjectMapperConfiguration {

  /**
   * Create object mapper object mapper.
   *
   * @return the object mapper
   */
  @Bean
    public ObjectMapper createObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModules(new JavaTimeModule());
        return mapper;
    }
}
