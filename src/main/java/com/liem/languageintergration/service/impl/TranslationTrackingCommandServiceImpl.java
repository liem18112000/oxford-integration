package com.liem.languageintergration.service.impl;

import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.excpetions.MappingException;
import com.liem.languageintergration.excpetions.TranslateTrackingException;
import com.liem.languageintergration.mapper.TranslationTrackingMapper;
import com.liem.languageintergration.repository.TranslationTrackingRepository;
import com.liem.languageintergration.service.TranslationTrackingCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class TranslationTrackingCommandServiceImpl implements
    TranslationTrackingCommandService<TranslationTrackingDto> {

    private final TranslationTrackingRepository repository;

    private final TranslationTrackingMapper mapper;

    /**
     * Track translation translate dto.
     *
     * @param responseDto the response dto
     * @return the translation dto
     */
    @Override
    public Mono<TranslationTrackingDto> trackTranslation(
        final TranslationTrackingDto responseDto) {
        log.info("Track translation");
        return Mono.just(responseDto)
            .map(this.mapper::toEntity)
            .flatMap(this.repository::save)
            .map(this.mapper::toDto)
            .onErrorResume(MappingException.class::isInstance, Mono::error)
            .onErrorResume(ex -> !(ex instanceof MappingException),
                throwable -> Mono.error(new TranslateTrackingException(
                "Track translation failed", throwable)))
            .doOnSuccess(dto -> log.info("Track translation success: {}", dto))
            .switchIfEmpty(Mono.error(new TranslateTrackingException(
                "TranslationTrackingDto is null")));
    }
}
