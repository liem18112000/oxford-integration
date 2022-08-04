package com.liem.languageintergration.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

/**
 * The type Translation detail dto.
 */
@JsonInclude(Include.NON_NULL)
@SuperBuilder
public class TranslationDetailDto {

  /**
   * The Target language.
   */
  @JsonProperty("target_language")
  protected String targetLanguage;

  /**
   * The Context.
   */
  @JsonProperty("context")
  protected String context;

  /**
   * The Word.
   */
  @JsonProperty("word")
  protected String word;
}
