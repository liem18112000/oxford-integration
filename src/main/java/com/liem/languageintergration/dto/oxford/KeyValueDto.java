package com.liem.languageintergration.dto.oxford;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Key value dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class KeyValueDto {

  /**
   * The Id.
   */
  protected String id;

  /**
   * The Text.
   */
  protected String text;

}
