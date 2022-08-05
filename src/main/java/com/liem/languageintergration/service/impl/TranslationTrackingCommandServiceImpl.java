package com.liem.languageintergration.service.impl;

import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.excpetions.MappingException;
import com.liem.languageintergration.excpetions.TranslateTrackingException;
import com.liem.languageintergration.mapper.TranslationTrackingMapper;
import com.liem.languageintergration.repository.TranslationTrackingRepository;
import com.liem.languageintergration.service.TranslationTrackingCommandService;
import java.util.Optional;
import java.util.function.Function;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * The type Translation tracking command service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class TranslationTrackingCommandServiceImpl implements
    TranslationTrackingCommandService<TranslationTrackingDto> {

    /**
     * The Repository.
     */
    private final TranslationTrackingRepository repository;

    /**
     * The Mapper.
     */
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
        final var sourceLanguage = getByOptional(responseDto,
            TranslationTrackingDto::getSourceLanguage);
        final var targetLanguage = getByOptional(responseDto,
            TranslationTrackingDto::getTargetLanguage);
        final var wordInSourceLanguage = getByOptional(responseDto,
            TranslationTrackingDto::getWordInSourceLanguage);
        return this.repository.findBySourceLanguageAndTargetLanguageAndWordInSourceLanguage(
            sourceLanguage, targetLanguage, wordInSourceLanguage)
            .switchIfEmpty(
                Mono.just(responseDto)
                    .map(this.mapper::toEntity)
                    .flatMap(this.repository::save)
            ).map(this.mapper::toDto)
            .onErrorResume(MappingException.class::isInstance, Mono::error)
            .onErrorResume(ex -> !(ex instanceof MappingException),
                throwable -> Mono.error(new TranslateTrackingException(
                    "Track translation failed", throwable)))
            .doOnSuccess(dto -> log.info("Track translation success: {}", dto));
    }

    /**
     * Gets by optional.
     *
     * @param responseDto the response dto
     * @param map         the map
     * @return the by optional
     */
    private String getByOptional(
        final TranslationTrackingDto responseDto,
        final @NotNull Function<TranslationTrackingDto, String> map) {
        return Optional.ofNullable(responseDto).map(map).orElse(StringUtils.EMPTY);
    }
}
