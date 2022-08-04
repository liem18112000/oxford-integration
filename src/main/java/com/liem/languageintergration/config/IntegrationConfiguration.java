package com.liem.languageintergration.config;

import java.time.Duration;
import java.util.Set;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * The type Integration configuration.
 */
@Configuration
@Getter
public class IntegrationConfiguration {

  /**
   * The App id.
   */
  @Value("${integration.app-id}")
  private String appId;

  /**
   * The App secret.
   */
  @Value("${integration.app-secret}")
  private String appSecret;

  /**
   * The Base url.
   */
  @Value("${integration.base-url}")
  private String baseUrl;

  /**
   * The Request timeout.
   */
  @Value("${integration.request-timeout}")
  private Duration requestTimeout;

  /**
   * The Request retry.
   */
  @Value("${integration.request-retry}")
  private Integer requestRetry;

  /**
   * The Cache duration.
   */
  @Value("${integration.cache-duration}")
  private Duration cacheDuration;

  /**
   * The Failed statuses.
   */
  private final Set<HttpStatus> failedStatuses = Set.of(
      HttpStatus.INTERNAL_SERVER_ERROR,
      HttpStatus.UNAUTHORIZED,
      HttpStatus.FORBIDDEN,
      HttpStatus.GATEWAY_TIMEOUT,
      HttpStatus.BAD_GATEWAY,
      HttpStatus.SERVICE_UNAVAILABLE
  );

}
