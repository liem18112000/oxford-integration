package com.liem.languageintergration.controller.advice;

import com.liem.languageintergration.dto.responses.ApiResponse;
import com.liem.languageintergration.dto.responses.ApiResponseCode;
import com.liem.languageintergration.excpetions.MappingException;
import com.liem.languageintergration.excpetions.TranslateTrackingException;
import com.liem.languageintergration.excpetions.TranslationException;
import java.io.IOException;
import java.lang.invoke.StringConcatException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * The type General controller advice.
 */
@Slf4j
@RestControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Handle translate exception mono.
   *
   * @param ex the ex
   * @return the mono
   */
  @ExceptionHandler({
      TranslateTrackingException.class,
      TranslationException.class
  })
  public Mono<ResponseEntity<ApiResponse<?>>> handleTranslateException(
      final @NotNull TranslationException ex) {
    log.error("Request error: ", ex);
    final ApiResponse<Object> body = buildBodyFromException(
        ex, ApiResponseCode.TRANSLATION_FAILED);
    return Mono.just(ResponseEntity.status(ex.getStatus()).body(body));
  }

  /**
   * Handle mapping exception mono.
   *
   * @param ex the ex
   * @return the mono
   */
  @ExceptionHandler({
      MappingException.class,
  })
  public Mono<ResponseEntity<ApiResponse<?>>> handleMappingException(
      final @NotNull MappingException ex) {
    log.error("Request error: ", ex);
    final ApiResponse<Object> body = buildBodyFromException(
        ex, ApiResponseCode.MAPPING_FAILED);
    return Mono.just(ResponseEntity.badRequest().body(body));
  }

  /**
   * Handle illegal exception mono.
   *
   * @param ex the ex
   * @return the mono
   */
  @ExceptionHandler({
      IllegalStateException.class,
      IllegalArgumentException.class,
      ConstraintViolationException.class,
      ConstraintDeclarationException.class,
      ConstraintDefinitionException.class
  })
  public Mono<ResponseEntity<ApiResponse<?>>> handleIllegalException(
      final @NotNull Exception ex) {
    log.error("Request error: ", ex);
    final ApiResponse<Object> body = buildBodyFromException(
        ex, ApiResponseCode.MAPPING_FAILED);
    return Mono.just(ResponseEntity.badRequest().body(body));
  }

  /**
   * Handle others exception mono.
   *
   * @param ex the ex
   * @return the mono
   */
  @ExceptionHandler({
      NullPointerException.class,
      StringConcatException.class,
      IndexOutOfBoundsException.class,
      IOException.class
  })
  public Mono<ResponseEntity<ApiResponse<?>>> handleOthersException(
      final @NotNull Exception ex) {
    log.error("Request error: ", ex);
    final ApiResponse<Object> body = buildBodyFromException(
        ex, ApiResponseCode.SERVER_ERROR);
    return Mono.just(ResponseEntity.internalServerError().body(body));
  }

  /**
   * Build body from exception api response.
   *
   * @param ex          the ex
   * @param apiResponse the api response
   * @return the api response
   */
  protected ApiResponse<Object> buildBodyFromException(
      final Exception ex, final ApiResponseCode apiResponse) {
    final var code = apiResponse.getCode();
    final var message = String.format("%s: %s",
        apiResponse.getMessage(), ex.getMessage());
    return new ApiResponse<>(code, message);
  }
}
