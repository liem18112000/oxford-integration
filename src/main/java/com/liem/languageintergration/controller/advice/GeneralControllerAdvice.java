package com.liem.languageintergration.controller.advice;

import com.liem.languageintergration.dto.responses.ApiResponse;
import com.liem.languageintergration.dto.responses.ApiResponseCode;
import com.liem.languageintergration.excpetions.TranslationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler({
      TranslationException.class
  })
  public Mono<ResponseEntity<ApiResponse<?>>> handleTranslateException(TranslationException ex) {
    log.error("Request error: ", ex);
    final var body = new ApiResponse<>(
        ApiResponseCode.SERVER_ERROR.getCode(), ex.getMessage());
    return Mono.just(ResponseEntity.status(ex.getStatus()).body(body));
  }
}
