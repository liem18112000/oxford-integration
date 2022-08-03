package com.liem.languageintergration.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Lexical entry detail dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class LexicalEntryDetailDto {

  /**
   * The Grammatical features.
   */
  protected List<GrammaticalFeatureDto> grammaticalFeatures;

  /**
   * The Senses.
   */
  protected List<SenseDto> senses;
}
