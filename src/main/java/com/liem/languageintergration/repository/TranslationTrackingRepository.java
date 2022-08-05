package com.liem.languageintergration.repository;


import com.liem.languageintergration.entity.TranslationTrackingEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TranslationTrackingRepository
    extends ReactiveMongoRepository<TranslationTrackingEntity, String> {

}
