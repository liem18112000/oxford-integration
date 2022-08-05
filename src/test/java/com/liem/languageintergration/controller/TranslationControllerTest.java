package com.liem.languageintergration.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.liem.languageintergration.dto.oxford.EntryDto;
import com.liem.languageintergration.dto.oxford.TranslationDto;
import com.liem.languageintergration.dto.responses.TranslationResponseDto;
import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.mapper.TranslationResponseMapper;
import com.liem.languageintergration.repository.TranslationTrackingRepository;
import com.liem.languageintergration.service.TranslationService;
import com.liem.languageintergration.service.TranslationTrackingQueryService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(value = TranslationController.class)
class TranslationControllerTest {


    @MockBean
    private TranslationResponseMapper mapper;

    @MockBean
    private TranslationService<EntryDto, TranslationDto> translationService;

    @MockBean
    private TranslationTrackingQueryService<TranslationTrackingDto> queryService;

    @MockBean
    TranslationTrackingRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void translate() {
        var mockData = mock(TranslationDto.class);
        when(this.translationService.translate(Mockito.any(EntryDto.class)))
            .thenReturn(Mono.just(mockData));
        when(this.mapper.toResponse(Mockito.any(TranslationDto.class)))
            .thenReturn(TranslationResponseDto.builder().build());
        webClient.get()
            .uri("/translate?word=king&sourceLang=en&targetLang=ar")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.code").isEqualTo("000000")
            .jsonPath("$.message").isEqualTo("Success")
        ;

        verify(translationService, times(1)).translate(Mockito.any(EntryDto.class));
        verifyNoMoreInteractions(translationService);
    }

    @Test
    void searchTracking() {
        when(this.queryService.getAll(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(Mono.just(new PageImpl<>(List.of(new TranslationTrackingDto()))));
        webClient.get()
            .uri("/translate/tracking")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.code").isEqualTo("000000")
            .jsonPath("$.message").isEqualTo("Success")
        ;

        verify(queryService, times(1))
            .getAll(Mockito.anyInt(), Mockito.anyInt());
        verifyNoMoreInteractions(queryService);
    }
}