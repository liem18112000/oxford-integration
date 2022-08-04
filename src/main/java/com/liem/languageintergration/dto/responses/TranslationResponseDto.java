package com.liem.languageintergration.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TranslationResponseDto {

  @JsonProperty("word_in_source_language")
  protected String wordInSourceLanguage;

  @Builder.Default
  @JsonProperty("translations")
  protected List<TranslationDetailDto> translations = new ArrayList<>();
}
