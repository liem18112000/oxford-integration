package com.liem.languageintergration.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Translation request dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TranslationDto {

  /**
   * The Id.
   */
  protected String id;

  /**
   * The Metadata.
   */
  protected MetadataDto metadata;

  /**
   * The Results.
   */
  protected List<TranslationEntryDto> results;

  /**
   * The Word.
   */
  protected String word;

}
