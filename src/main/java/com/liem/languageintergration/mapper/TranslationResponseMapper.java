package com.liem.languageintergration.mapper;

import com.liem.languageintergration.dto.oxford.LexicalEntryDto;
import com.liem.languageintergration.dto.oxford.SenseDto;
import com.liem.languageintergration.dto.oxford.TranslationDto;
import com.liem.languageintergration.dto.responses.TranslationDetailDto;
import com.liem.languageintergration.dto.responses.TranslationResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Translation response mapper.
 */
@Component
public class TranslationResponseMapper {

  /**
   * To response translation response dto.
   *
   * @param dto the dto
   * @return the translation response dto
   */
  public TranslationResponseDto toResponse(
      final @NotNull TranslationDto dto) {
    if (dto == null) {
      return null;
    }

    var list = new ArrayList<TranslationDetailDto>();
    dto.getResults().forEach(i -> list.addAll(this.toTranslations(i.getLexicalEntries())));
    return TranslationResponseDto.builder()
        .wordInSourceLanguage(dto.getWord())
        .translations(list)
        .build();
  }

  /**
   * To translations list.
   *
   * @param lexicalEntries the lexical entries
   * @return the list
   */
  private List<TranslationDetailDto> toTranslations(
      final @NotNull List<LexicalEntryDto> lexicalEntries) {
    var list = new ArrayList<TranslationDetailDto>();
    lexicalEntries.forEach(l -> l.getEntries()
        .forEach(e -> e.getSenses()
            .forEach(s -> list.add(toTranslation(s)))
        )
    );
    return list;
  }

  /**
   * To translation translation detail dto.
   *
   * @param s the s
   * @return the translation detail dto
   */
  private TranslationDetailDto toTranslation(SenseDto s) {
    String context = null;
    List<Map<String, String>> notes = s.getNotes();
    if (notes != null && !notes.isEmpty()) {
      context = notes.get(0).get("text");
    } else {
      List<Map<String, String>> registers = s.getRegisters();
      if (registers != null && !registers.isEmpty()) {
        context = registers.get(0).get("text");
      }
    }

    String word = null, lang = null;
    List<Map<String, String>> translations = s.getTranslations();
    if (translations != null && !translations.isEmpty()) {
      word = translations.get(0).get("text");
      lang = translations.get(0).get("language");
    }

    return TranslationDetailDto.builder()
        .word(word)
        .targetLanguage(lang)
        .context(context)
        .build();
  }
}
