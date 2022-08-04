package com.liem.languageintergration.dto.oxford;

import lombok.Getter;
import lombok.ToString;

/**
 * The enum Response code.
 */
@ToString
@Getter
public enum ResponseCode {

  /**
   * Success response code.
   */
  SUCCESS("000000", "SUCCESS"),
  /**
   * The Invalid client params.
   */
  INVALID_CLIENT_PARAMS("000001", "INVALID CLIENT PARAMS"),

  /**
   * The Unauthenticated request.
   */
  UNAUTHENTICATED_REQUEST("000002", "UNAUTHENTICATED REQUEST"),

  /**
   * The Unauthorized request.
   */
  UNAUTHORIZED_REQUEST("000003", "UNAUTHORIZED REQUEST"),

  /**
   * Unknown error response code.
   */
  UNKNOWN_ERROR("000004", "UNKNOWN_ERROR")
  ;

  /**
   * The Code.
   */
  final String code;

  /**
   * The Message.
   */
  final String message;

  /**
   * Instantiates a new Response code.
   *
   * @param code    the code
   * @param message the message
   */
  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
