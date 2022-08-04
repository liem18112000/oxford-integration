package com.liem.languageintergration.dto.oxford;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Entry dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EntryDto {

  /**
   * The Source lang.
   */
  @NotNull(message = "Source language must not be null")
  @NotBlank(message = "Source language must not be empty")
  protected String sourceLang;

  /**
   * The Target lang.
   */
  @NotNull(message = "Target language must not be null")
  @NotBlank(message = "Target language must not be empty")
  protected String targetLang;

  /**
   * The Word id.
   */
  @NotNull(message = "Word id must not be null")
  @NotBlank(message = "Word id must not be empty")
  protected String wordId;

}
