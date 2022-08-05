package com.liem.languageintergration.entity;

import com.liem.languageintergration.dto.responses.ApiResponseCode;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Translation tracking entity.
 */
@NoArgsConstructor
@SuperBuilder
@Document(collection = "translation-tracking")
@Data
public class TranslationTrackingEntity {

    /**
     * The Id.
     */
    @Id
    @Builder.Default
    protected String id = UUID.randomUUID().toString();

    /**
     * The Source language.
     */
    protected String sourceLanguage;

    /**
     * The Word in source language.
     */
    protected String wordInSourceLanguage;

    /**
     * The Target language.
     */
    protected String targetLanguage;

    /**
     * The Translation.
     */
    protected String translation;

    /**
     * The Is translated success.
     */
    @Builder.Default
    protected boolean translatedSuccess = true;

    /**
     * The Extra information.
     */
    @Builder.Default
    protected String extraInformation = ApiResponseCode.SUCCESS.getMessage();

    /**
     * The Translated at.
     */
    @Builder.Default
    protected String translatedAt = LocalDateTime.now().toString();
}
