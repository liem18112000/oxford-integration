package com.liem.languageintergration.service;

import reactor.core.publisher.Mono;

/**
 * The interface Translation tracking command service.
 *
 * @param <DTO> the type parameter
 */
public interface TranslationTrackingCommandService<DTO> {

    /**
     * Track translation translate dto.
     *
     * @param responseDto the response dto
     * @return the translation dto
     */
    Mono<DTO> trackTranslation(DTO responseDto);
}
