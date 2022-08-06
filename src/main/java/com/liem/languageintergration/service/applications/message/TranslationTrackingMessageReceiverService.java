package com.liem.languageintergration.service.applications.message;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liem.languageintergration.config.RabbitConfiguration;
import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.service.domains.TranslationTrackingCommandService;
import com.liem.languageintergration.service.utility.message.impl.AbstractRabbitMqMessageReceiverService;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

/**
 * The type Translation tracking message receiver service.
 */
@Slf4j
@Service
public class TranslationTrackingMessageReceiverService extends
    AbstractRabbitMqMessageReceiverService {

  /**
   * The Tracking service.
   */
  private final TranslationTrackingCommandService<TranslationTrackingDto> trackingService;

  /**
   * The Object mapper.
   */
  private final ObjectMapper objectMapper;

  /**
   * Instantiates a new Translation tracking message receiver service.
   *
   * @param connectionMono  the connection mono
   * @param receiver        the receiver
   * @param configuration   the configuration
   * @param trackingService the tracking service
   * @param objectMapper    the object mapper
   */
  public TranslationTrackingMessageReceiverService(
      @Autowired Mono<Connection> connectionMono,
      @Autowired Receiver receiver,
      RabbitConfiguration configuration,
      TranslationTrackingCommandService<TranslationTrackingDto> trackingService,
      ObjectMapper objectMapper) {
    super(connectionMono, receiver, configuration);
    this.trackingService = trackingService;
    this.objectMapper = objectMapper;
  }

  /**
   * Handle message.
   *
   * @param delivery the delivery
   */
  @Override
  protected void handleMessage(Delivery delivery) {
    TranslationTrackingDto dto;
    try {
      dto = this.objectMapper.readValue(
          delivery.getBody(), TranslationTrackingDto.class);
    } catch (IOException e) {
      log.error("Handle message failed", e);
      return;
    }
    this.trackingService.trackTranslation(dto).subscribe();
  }
}
