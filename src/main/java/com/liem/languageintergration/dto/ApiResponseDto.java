package com.liem.languageintergration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * The type Api response dto.
 *
 * @param <T> the type parameter
 */
@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class ApiResponseDto<T> {

  /**
   * The Code.
   */
  @Builder.Default
  protected ResponseCode code = ResponseCode.SUCCESS;

  /**
   * The Message.
   */
  @Builder.Default
  protected String message = null;

  /**
   * The Data.
   */
  protected T data;

}
