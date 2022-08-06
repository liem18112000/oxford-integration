package com.liem.languageintergration.service.domains.impl;

import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.mapper.TranslationTrackingMapper;
import com.liem.languageintergration.repository.TranslationTrackingRepository;
import com.liem.languageintergration.service.domains.TranslationTrackingQueryService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * The type Translation tracking query service.
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class TranslationTrackingQueryServiceImpl implements
    TranslationTrackingQueryService<TranslationTrackingDto> {

  /**
   * The Repository.
   */
  private final TranslationTrackingRepository repository;

  /**
   * The Mapper.
   */
  private final TranslationTrackingMapper mapper;

  /**
   * Gets all.
   *
   * @param page the page
   * @param size the size
   * @return the all
   */
  @Override
  public Mono<Page<TranslationTrackingDto>> getAll(final int page, final int size) {
    return  this.repository.findAll()
        .collectList()
        .map(items -> {
          if (items.isEmpty()) {
            return Page.empty();
          }
          final var totalElement = items.size();
          final var list = items.stream()
              .skip((long) Math.max(0, page) * size)
              .limit(size).map(this.mapper::toDto)
              .collect(Collectors.toList());
          var pageable = Pageable.ofSize(size);
          return new PageImpl<>(list, pageable, totalElement);
        });
  }
}
