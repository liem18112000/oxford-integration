package com.liem.languageintergration.dto.responses;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Api response.
 *
 * @param <T> the type parameter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  /**
   * The Code.
   */
  private String code = ApiResponseCode.SUCCESS.getCode();

  /**
   * The Message.
   */
  private String message = ApiResponseCode.SUCCESS.getMessage();

  /**
   * The Response.
   */
  private T response;

  /**
   * Instantiates a new Api response.
   *
   * @param response the response
   */
  public ApiResponse(T response) {
    this.response = response;
  }

  /**
   * Instantiates a new Api response.
   *
   * @param code    the code
   * @param message the message
   */
  public ApiResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * The Timestamp.
   */
  private final String timestamp = LocalDateTime.now().toString();

}
