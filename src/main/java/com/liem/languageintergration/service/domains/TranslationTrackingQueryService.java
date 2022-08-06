package com.liem.languageintergration.service.domains;

import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

/**
 * The interface Translation tracking query service.
 *
 * @param <DTO> the type parameter
 */
public interface TranslationTrackingQueryService<DTO> {

  /**
   * Gets all.
   *
   * @param page the page
   * @param size the size
   * @return the all
   */
  Mono<Page<DTO>> getAll(int page, int size);
}
