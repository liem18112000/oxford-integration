package com.liem.languageintergration.dto;

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
   * The Word id.
   */
  @NotNull(message = "Word id must not be null")
  @NotBlank(message = "Word id must not be empty")
  protected String wordId;

}
