package com.liem.languageintergration.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liem.languageintergration.dto.oxford.EntryDto;
import com.liem.languageintergration.dto.oxford.TranslationDto;
import com.liem.languageintergration.dto.responses.TranslationResponseDto;
import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.entity.TranslationTrackingEntity;
import com.liem.languageintergration.excpetions.MappingException;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Translation tracking mapper.
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class TranslationTrackingMapper {

    /**
     * The Object mapper.
     */
    private final ObjectMapper objectMapper;

    /**
     * The Response mapper.
     */
    private final TranslationResponseMapper responseMapper;

    /**
     * To entity translation tracking entity.
     *
     * @param dto the dto
     * @return the translation tracking entity
     */
    public TranslationTrackingEntity toEntity(final TranslationTrackingDto dto) {
        return Optional.ofNullable(dto)
            .map(d -> TranslationTrackingEntity.builder()
                .sourceLanguage(d.getSourceLanguage())
                .wordInSourceLanguage(d.getWordInSourceLanguage())
                .translatedSuccess(d.isTranslatedSuccess())
                .extraInformation(d.getExtraInformation())
                .translation(d.getTranslation())
                .build()
            ).orElseThrow(() -> {
                log.error("TranslationTrackingDto is null");
                throw new MappingException("TranslationTrackingDto is null");
            });

    }

    /**
     * To dto translation tracking dto.
     *
     * @param entity the entity
     * @return the translation tracking dto
     */
    public TranslationTrackingDto toDto(final TranslationTrackingEntity entity) {
        return Optional.ofNullable(entity)
            .map(e -> TranslationTrackingDto.builder()
                .id(e.getId())
                .sourceLanguage(e.getSourceLanguage())
                .translatedSuccess(e.isTranslatedSuccess())
                .extraInformation(e.getExtraInformation())
                .translation(e.getTranslation())
                .translatedAt(e.getTranslatedAt())
                .wordInSourceLanguage(e.getWordInSourceLanguage())
                .build()
            ).orElseGet(() -> {
                log.error("TranslationTrackingEntity is null");
                throw new MappingException("TranslationTrackingDto is null");
            });

    }

    /**
     * To dto translation tracking dto.
     *
     * @param dto      the dto
     * @param entryDto the entry dto
     * @return the translation tracking dto
     */
    public TranslationTrackingDto toDto(
        final TranslationDto dto,
        final @NotNull EntryDto entryDto) {
        return Optional.ofNullable(dto)
            .map(responseMapper::toResponse)
            .map(r -> {
                final String translation = getTranslation(r);
                return TranslationTrackingDto.builder()
                    .sourceLanguage(entryDto.getSourceLang())
                    .targetLanguage(entryDto.getTargetLang())
                    .translation(translation)
                    .wordInSourceLanguage(entryDto.getWordId())
                    .build();
            })
            .orElseGet(() -> {
                log.error("TranslationResponseDto is null");
                throw new MappingException("TranslationResponseDto is null");
            });
    }

    /**
     * Gets translation.
     *
     * @param responseDto the response dto
     * @return the translation
     */
    private String getTranslation(TranslationResponseDto responseDto) {
        try {
            return this.objectMapper
                .writeValueAsString(responseDto.getTranslations());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new MappingException(e.getMessage());
        }
    }
}
