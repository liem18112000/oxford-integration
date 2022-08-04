package com.liem.languageintergration.controller;

import static com.liem.languageintergration.config.TranslationRoute.TRANSLATE;

import com.liem.languageintergration.dto.oxford.EntryDto;
import com.liem.languageintergration.dto.oxford.TranslationDto;
import com.liem.languageintergration.dto.responses.ApiResponse;
import com.liem.languageintergration.dto.responses.TranslationResponseDto;
import com.liem.languageintergration.mapper.TranslationResponseMapper;
import com.liem.languageintergration.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The type Translation controller.
 */
@RequestMapping(TRANSLATE)
@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class TranslationController {

  private final TranslationResponseMapper mapper;

  /**
   * The Translation service.
   */
  private final TranslationService<EntryDto, TranslationDto> translationService;

  /**
   * Translate mono.
   *
   * @param sourceLang the source lang
   * @param targetLang the target lang
   * @param workId     the work id
   * @return the mono
   */
  @GetMapping("raw")
  public Mono<ResponseEntity<ApiResponse<TranslationDto>>> translateRaw(
      final @RequestParam String sourceLang,
      final @RequestParam String targetLang,
      final @RequestParam String workId) {
    final var request = EntryDto.builder()
        .sourceLang(sourceLang.toLowerCase())
        .targetLang(targetLang.toLowerCase())
        .wordId(workId.toLowerCase())
        .build();
    return this.translationService.translate(request)
        .map(body -> ResponseEntity.ok(new ApiResponse<>(body)));
  }

  /**
   * Translate mono.
   *
   * @param sourceLang the source lang
   * @param targetLang the target lang
   * @param workId     the work id
   * @return the mono
   */
  @GetMapping
  public Mono<ResponseEntity<ApiResponse<TranslationResponseDto>>> translate(
      final @RequestParam String sourceLang,
      final @RequestParam String targetLang,
      final @RequestParam String workId) {
    final var request = EntryDto.builder()
        .sourceLang(sourceLang.toLowerCase())
        .targetLang(targetLang.toLowerCase())
        .wordId(workId.toLowerCase())
        .build();
    return this.translationService.translate(request)
        .map(this.mapper::toResponse)
        .map(body -> ResponseEntity.ok(new ApiResponse<>(body)));
  }

}
