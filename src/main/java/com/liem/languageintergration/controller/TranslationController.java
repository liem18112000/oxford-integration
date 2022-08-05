package com.liem.languageintergration.controller;

import static com.liem.languageintergration.config.TranslationRoute.TRANSLATE;

import com.liem.languageintergration.dto.oxford.EntryDto;
import com.liem.languageintergration.dto.oxford.TranslationDto;
import com.liem.languageintergration.dto.responses.ApiResponse;
import com.liem.languageintergration.dto.responses.TranslationResponseDto;
import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.mapper.TranslationResponseMapper;
import com.liem.languageintergration.service.TranslationService;
import com.liem.languageintergration.service.TranslationTrackingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  /**
   * The Mapper.
   */
  private final TranslationResponseMapper mapper;

  /**
   * The Translation service.
   */
  private final TranslationService<EntryDto, TranslationDto> translationService;

  /**
   * The Query service.
   */
  private final TranslationTrackingQueryService<TranslationTrackingDto> queryService;

  /**
   * Translate mono.
   *
   * @param sourceLang the source lang
   * @param targetLang the target lang
   * @param word       the word id
   * @return the mono
   */
  @GetMapping
  public Mono<ResponseEntity<ApiResponse<TranslationResponseDto>>> translate(
      final @RequestParam String sourceLang,
      final @RequestParam String targetLang,
      final @RequestParam String word) {
    final var request = EntryDto.builder()
        .sourceLang(sourceLang.toLowerCase())
        .targetLang(targetLang.toLowerCase())
        .wordId(word.toLowerCase())
        .build();
    return this.translationService.translate(request)
        .map(this.mapper::toResponse)
        .map(body -> ResponseEntity.ok(new ApiResponse<>(body)));
  }

  /**
   * Search tracking mono.
   *
   * @param page the page
   * @param size the size
   * @return the mono
   */
  @GetMapping("tracking")
  public Mono<ResponseEntity<ApiResponse<Page<TranslationTrackingDto>>>> searchTracking(
      final @RequestParam(defaultValue = "0") int page,
      final @RequestParam(defaultValue = "10") int size) {
    return this.queryService.getAll(page, size)
        .map(ApiResponse::new).map(ResponseEntity::ok);
  }

}
