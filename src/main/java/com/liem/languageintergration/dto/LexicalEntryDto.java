package com.liem.languageintergration.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Lexical entry dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class LexicalEntryDto {

  /**
   * The Entries.
   */
  protected List<LexicalEntryDetailDto> entries;

  /**
   * The Language.
   */
  protected String language;

  /**
   * The Lexical category.
   */
  protected KeyValueDto lexicalCategory;

  /**
   * The Text.
   */
  protected String text;
}
