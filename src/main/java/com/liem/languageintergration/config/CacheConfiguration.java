package com.liem.languageintergration.config;

import com.liem.languageintergration.dto.oxford.TranslationDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * The type Cache configuration.
 */
@Configuration
public class CacheConfiguration {

  /**
   * Reactive redis template reactive redis template.
   *
   * @param factory the factory
   * @return the reactive redis template
   */
  @Bean
  public ReactiveRedisTemplate<String, TranslationDto> reactiveRedisTemplate(
      ReactiveRedisConnectionFactory factory) {
    StringRedisSerializer keySerializer = new StringRedisSerializer();
    Jackson2JsonRedisSerializer<TranslationDto> valueSerializer =
        new Jackson2JsonRedisSerializer<>(TranslationDto.class);
    RedisSerializationContext.RedisSerializationContextBuilder<String, TranslationDto> builder =
        RedisSerializationContext.newSerializationContext(keySerializer);
    RedisSerializationContext<String, TranslationDto> context =
        builder.value(valueSerializer).build();
    return new ReactiveRedisTemplate<>(factory, context);
  }

  /**
   * Reactive value ops reactive value operations.
   *
   * @param reactiveRedisTemplate the reactive redis template
   * @return the reactive value operations
   */
  @Bean
  public ReactiveValueOperations<String, TranslationDto> reactiveValueOps(
      ReactiveRedisTemplate<String, TranslationDto> reactiveRedisTemplate) {
    return reactiveRedisTemplate.opsForValue();
  }

}
