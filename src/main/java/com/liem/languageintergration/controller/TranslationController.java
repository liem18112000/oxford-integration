package com.liem.languageintergration.controller;

import com.liem.languageintergration.dto.ApiResponseDto;
import com.liem.languageintergration.dto.EntryDto;
import com.liem.languageintergration.dto.TranslationDto;
import com.liem.languageintergration.service.TranslationService;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping
@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class TranslationController {

  private final TranslationService<EntryDto, ResponseEntity<TranslationDto>> translationService;

  @GetMapping("translate")
  public Mono<ResponseEntity<TranslationDto>> translate(
      final @RequestParam String sourceLang,
      final @RequestParam String workId) {

    final var request = EntryDto.builder()
        .sourceLang(sourceLang.toLowerCase())
        .wordId(workId.toLowerCase())
        .build();
    return this.translationService.translate(request);
  }

}
