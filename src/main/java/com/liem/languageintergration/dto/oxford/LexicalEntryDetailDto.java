package com.liem.languageintergration.dto.oxford;

import java.util.List;
import java.util.Map;
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
  protected List<Map<String, Object>> pronunciations;

  /**
   * The Senses.
   */
  protected List<SenseDto> senses;
}
