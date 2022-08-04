package com.liem.languageintergration.dto.oxford;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Metadata dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class MetadataDto {

  /**
   * The Operation.
   */
  protected String operation;

  /**
   * The Provider.
   */
  protected String provider;

  /**
   * The Schema.
   */
  protected String schema;
}
