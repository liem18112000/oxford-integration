package com.liem.languageintergration.service;

import reactor.core.publisher.Mono;

/**
 * The interface Translation service.
 */
public interface TranslationService<ENTRY, TRANSLATE> {

  /**
   * Translate translation dto.
   *
   * @param entry the entry
   * @return the translation dto
   */
  Mono<TRANSLATE> translate(ENTRY entry);
}
