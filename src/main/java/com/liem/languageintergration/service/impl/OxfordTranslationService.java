package com.liem.languageintergration.service.impl;

import static com.liem.languageintergration.constants.ClientConstants.APP_ID_HEADER_NAME;
import static com.liem.languageintergration.constants.ClientConstants.APP_KEY_HEADER_NAME;
import static com.liem.languageintergration.constants.TranslationServiceRoute.TRANSLATIONS;

import com.liem.languageintergration.config.IntegrationConfiguration;
import com.liem.languageintergration.dto.oxford.EntryDto;
import com.liem.languageintergration.dto.oxford.TranslationDto;
import com.liem.languageintergration.excpetions.TranslationException;
import com.liem.languageintergration.factory.ClientFactory;
import com.liem.languageintergration.service.TranslationService;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

/**
 * The type Oxford translation service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class OxfordTranslationService
    implements TranslationService<EntryDto, TranslationDto> {

  /**
   * The Client factory.
   */
  private final ClientFactory clientFactory;

  /**
   * The Configuration.
   */
  private final IntegrationConfiguration configuration;

  /**
   * The Value operations.
   */
  private final ReactiveValueOperations<String, TranslationDto> valueOperations;

  /**
   * Translate translation dto.
   *
   * @param entry the entry
   * @return the translation dto
   */
  @Override
  public Mono<TranslationDto> translate(final @NotNull @Valid EntryDto entry) {
    final var timeout = configuration.getRequestTimeout();
    final var retry = configuration.getRequestRetry();
    final var cache = configuration.getCacheDuration();
    final var baseUrl = buildTranslateBaseUrl(entry);
    final var httpClient = clientFactory.createHttpClient(timeout);
    final var webClient = clientFactory.createReactiveWebClient(httpClient);
    try {
      return webClient
          .get()
          .uri(new URI(baseUrl))
          .headers(httpHeaders -> httpHeaders.addAll(this.getAllHeaders()))
          .accept(MediaType.APPLICATION_JSON)
          .acceptCharset(StandardCharsets.UTF_8)
          .retrieve()
          .onStatus(status -> !status.is2xxSuccessful(), clientResponse -> {
            log.error("Error client response: {}", clientResponse);
            throw new TranslationException(clientResponse.statusCode(), "Error client response");
          })
          .bodyToMono(TranslationDto.class)
          .retry(retry)
          .cache(cache)
          .doOnSuccess(dto -> log.info("Request to Oxford success"))
          .onErrorResume(Mono::error);
    } catch (URISyntaxException ex) {
      log.error("Create URI failed with base url '{}' : {}", baseUrl, ex.getMessage());
      throw new TranslationException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }

  /**
   * Build translate base url string.
   *
   * @param entry the entry
   * @return the string
   */
  private String buildTranslateBaseUrl(EntryDto entry) {
    final var sourceLang = entry.getSourceLang();
    final var targetLang = entry.getTargetLang();
    final var wordId = entry.getWordId();
    return String.format("%s/%s/%s/%s/%s",
        configuration.getBaseUrl(), TRANSLATIONS, sourceLang, targetLang, wordId);
  }

  /**
   * Gets all headers.
   *
   * @return the all headers
   */
  private MultiValueMap<String, String> getAllHeaders() {
    final var appId = configuration.getAppId();
    final var appSecret = configuration.getAppSecret();
    var httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(APP_ID_HEADER_NAME, appId);
    httpHeaders.add(APP_KEY_HEADER_NAME, appSecret);
    return httpHeaders;
  }
}
