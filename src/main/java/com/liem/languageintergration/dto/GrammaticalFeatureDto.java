package com.liem.languageintergration.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Grammatical feature dto.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class GrammaticalFeatureDto extends KeyValueDto {

  /**
   * The Type.
   */
  protected String type;
}
