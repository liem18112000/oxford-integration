package com.liem.languageintergration.dto.responses;

import lombok.Getter;

/**
 * The enum Api response code.
 */
@Getter
public enum ApiResponseCode {

  /**
   * Success api response code.
   */
  SUCCESS("000000", "Success"),

  /**
   * The Invalid client params.
   */
  INVALID_CLIENT_PARAMS("000001", "Invalid client params"),

  /**
   * The Server error.
   */
  SERVER_ERROR("000002", "Server error"),

  /**
   * The Translation failed.
   */
  TRANSLATION_FAILED("000003", "There is an error while translation"),

  /**
   * The Mapping failed.
   */
  MAPPING_FAILED("000004", "There is an error while mapping data")
  ;

  /**
   * The Code.
   */
  private final String code;

  /**
   * The Message.
   */
  private final String message;

  /**
   * Instantiates a new Api response code.
   *
   * @param code    the code
   * @param message the message
   */
  ApiResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
