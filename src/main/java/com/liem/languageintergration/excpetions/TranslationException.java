package com.liem.languageintergration.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Translation exception.
 */
public class TranslationException extends ResponseStatusException {

  /**
   * Instantiates a new Translation exception.
   *
   * @param status the status
   */
  public TranslationException(HttpStatus status) {
    super(status);
  }

  /**
   * Instantiates a new Translation exception.
   *
   * @param status the status
   * @param reason the reason
   */
  public TranslationException(HttpStatus status, String reason) {
    super(status, reason);
  }

  /**
   * Instantiates a new Translation exception.
   *
   * @param status the status
   * @param reason the reason
   * @param cause  the cause
   */
  public TranslationException(HttpStatus status, String reason, Throwable cause) {
    super(status, reason, cause);
  }

  /**
   * Instantiates a new Translation exception.
   *
   * @param rawStatusCode the raw status code
   * @param reason        the reason
   * @param cause         the cause
   */
  public TranslationException(int rawStatusCode, String reason, Throwable cause) {
    super(rawStatusCode, reason, cause);
  }
}
