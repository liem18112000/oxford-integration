package com.liem.languageintergration.dto.tracking;

import com.liem.languageintergration.dto.responses.ApiResponseCode;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Translation tracking dto.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TranslationTrackingDto implements Serializable {

    /**
     * The Id.
     */
    protected String id;

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
