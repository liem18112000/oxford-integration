package com.liem.languageintergration.dto.tracking;

import java.io.Serializable;
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
    protected boolean translatedSuccess;

    /**
     * The Extra information.
     */
    protected String extraInformation;

    /**
     * The Translated at.
     */
    protected String translatedAt;

}
