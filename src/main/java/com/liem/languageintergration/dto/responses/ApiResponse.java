package com.liem.languageintergration.dto.responses;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  private String code = ApiResponseCode.SUCCESS.getCode();

  private String message = ApiResponseCode.SUCCESS.getMessage();

  private T response;

  public ApiResponse(T response) {
    this.response = response;
  }

  public ApiResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }

  private final String timestamp = LocalDateTime.now().toString();

}
