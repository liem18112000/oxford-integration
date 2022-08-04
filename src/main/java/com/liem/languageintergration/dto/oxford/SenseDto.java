package com.liem.languageintergration.dto.oxford;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Sense dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SenseDto {

  /**
   * The Id.
   */
  protected String id;

  /**
   * The Notes.
   */
  protected List<Map<String, String>> notes;

  /**
   * The Notes.
   */
  protected List<Map<String, String>> registers;

  /**
   * The Translations.
   */
  protected List<Map<String, String>> translations;

}
