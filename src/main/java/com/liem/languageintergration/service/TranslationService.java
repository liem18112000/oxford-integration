package com.liem.languageintergration.service;

import reactor.core.publisher.Mono;

/**
 * The interface Translation service.
 *
 * @param <ENTRY>     the type parameter
 * @param <TRANSLATE> the type parameter
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
