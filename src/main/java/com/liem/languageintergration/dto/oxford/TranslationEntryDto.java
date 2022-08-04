package com.liem.languageintergration.dto.oxford;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Translation entry dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TranslationEntryDto {

  /**
   * The Id.
   */
  protected String id;

  /**
   * The Language.
   */
  protected String language;

  /**
   * The Type.
   */
  protected String type;

  /**
   * The Lexical entries.
   */
  protected List<LexicalEntryDto> lexicalEntries;

  /**
   * The Word.
   */
  protected String word;
}
