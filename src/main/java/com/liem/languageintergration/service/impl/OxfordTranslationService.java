package com.liem.languageintergration.service.impl;

import static com.liem.languageintergration.constants.ClientConstants.APP_ID_HEADER_NAME;
import static com.liem.languageintergration.constants.ClientConstants.APP_KEY_HEADER_NAME;

import com.liem.languageintergration.config.IntegrationConfiguration;
import com.liem.languageintergration.dto.EntryDto;
import com.liem.languageintergration.dto.TranslationDto;
import com.liem.languageintergration.excpetions.TranslationException;
import com.liem.languageintergration.factory.ClientFactory;
import com.liem.languageintergration.service.TranslationService;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    implements TranslationService<EntryDto, ResponseEntity<TranslationDto>> {

  /**
   * The Client factory.
   */
  private final ClientFactory clientFactory;

  /**
   * The Configuration.
   */
  private final IntegrationConfiguration configuration;

  /**
   * Translate translation dto.
   *
   * @param entry the entry
   * @return the translation dto
   */
  @Override
  public Mono<ResponseEntity<TranslationDto>> translate(final @NotNull @Valid EntryDto entry) {
    final var timeout = configuration.getRequestTimeout();
    final var lang = entry.getSourceLang();
    final var work = entry.getWordId();
    final var baseUrl = String.format("%s/%s/%s/%s",
        configuration.getBaseUrl(), "entries", lang, work);
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
          .onStatus(configuration.getFailedStatuses()::contains, clientResponse -> {
            log.error("Error client response: {}", clientResponse);
            throw new TranslationException(clientResponse.statusCode(), "Error client response");
          })
          .toEntity(TranslationDto.class)
          .retry(3)
          .cache(Duration.ofMinutes(5))
          .doOnSuccess(dto -> log.info("Request to Oxford success"));
    } catch (URISyntaxException ex) {
      log.error("Create URI failed with base url '{}' : {}", baseUrl, ex.getMessage());
      throw new TranslationException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
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
