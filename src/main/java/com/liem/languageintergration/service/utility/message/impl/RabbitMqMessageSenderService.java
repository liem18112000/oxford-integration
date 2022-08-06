package com.liem.languageintergration.service.utility.message.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liem.languageintergration.config.RabbitConfiguration;
import com.liem.languageintergration.dto.tracking.TranslationTrackingDto;
import com.liem.languageintergration.service.utility.message.MessageSenderService;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Sender;

/**
 * The type Rabbit mq message sender service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RabbitMqMessageSenderService
    implements MessageSenderService<TranslationTrackingDto> {

  /**
   * The Sender.
   */
  private final Sender sender;

  /**
   * The Configuration.
   */
  private final RabbitConfiguration configuration;

  /**
   * The Object mapper.
   */
  private final ObjectMapper objectMapper;

  /**
   * Send message.
   *
   * @param message the message
   * @return the message
   */
  @Override
  public final TranslationTrackingDto send(TranslationTrackingDto message) {
    final var queueName = configuration.getQueueName();
    final byte[] body;
    try {
      body = this.convertMessageToByte(message);
    } catch (Exception e) {
      log.error("Convert message failed: {}", e.getMessage());
      return null;
    }
    final var outboundMessage = Flux.just(
        new OutboundMessage("", queueName, body));
    sender.declareQueue(QueueSpecification.queue(queueName))
        .thenMany(sender.sendWithPublishConfirms(outboundMessage))
        .doOnError(e -> log.error("Send failed", e))
        .subscribe(m -> {
          if(m.isAck()) {
            log.info("Message sent : {}", m);
          }
        });
    return message;
  }

  /**
   * Convert message to byte array
   *
   * @param translationTrackingDto the message
   * @return byte array
   */
  protected byte[] convertMessageToByte(
      final @NotNull TranslationTrackingDto translationTrackingDto) {
    try {
      return this.objectMapper.writeValueAsBytes(translationTrackingDto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
