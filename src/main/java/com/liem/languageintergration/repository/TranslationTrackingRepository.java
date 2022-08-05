package com.liem.languageintergration.repository;

import com.liem.languageintergration.entity.TranslationTrackingEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Translation tracking repository.
 */
public interface TranslationTrackingRepository
    extends ReactiveMongoRepository<TranslationTrackingEntity, String> {

    /**
     * Find by source language and target language and word in source language mono.
     *
     * @param sourceLanguage       the source language
     * @param targetLanguage       the target language
     * @param wordInSourceLanguage the word in source language
     * @return the mono
     */
    Mono<TranslationTrackingEntity> findBySourceLanguageAndTargetLanguageAndWordInSourceLanguage(
        String sourceLanguage, String targetLanguage, String wordInSourceLanguage);

    /**
     * Find all by source language and target language flux.
     *
     * @param sourceLanguage the source language
     * @param targetLanguage the target language
     * @return the flux
     */
    Flux<TranslationTrackingEntity> findAllBySourceLanguageAndTargetLanguage(
        String sourceLanguage, String targetLanguage);

}
