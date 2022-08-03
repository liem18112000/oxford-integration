package com.liem.languageintergration.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Sense dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class SenseDto {

  /**
   * The Definitions.
   */
  protected List<String> definitions;

  /**
   * The Domain classes.
   */
  protected List<KeyValueDto> domainClasses;

  /**
   * The Id.
   */
  protected String id;

  /**
   * The Regions.
   */
  protected List<KeyValueDto> regions;

  /**
   * The Semantic classes.
   */
  protected List<KeyValueDto> semanticClasses;

  /**
   * The Short definitions.
   */
  protected List<String> shortDefinitions;

}
