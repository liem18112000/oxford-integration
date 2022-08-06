package com.liem.languageintergration.service.utility.message.impl;

import com.liem.languageintergration.config.RabbitConfiguration;
import com.liem.languageintergration.service.utility.message.MessageReceiverService;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

/**
 * The type Abstract rabbit mq message receiver service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public abstract class AbstractRabbitMqMessageReceiverService
    implements MessageReceiverService<Disposable> {

  /**
   * The Connection mono.
   */
  private final Mono<Connection> connectionMono;

  /**
   * The Receiver.
   */
  private final Receiver receiver;

  /**
   * The Configuration.
   */
  private final RabbitConfiguration configuration;

  /**
   * Receive message.
   *
   * @return the disposable
   */
  @Override
  public final Disposable receive() {
    final var queueName = this.configuration.getQueueName();
    return receiver
        .consumeAutoAck(queueName)
        .onErrorResume(Mono::error)
        .subscribe(m -> {
          log.info("Received message {}", new String(m.getBody()));
          this.handleMessage(m);
        });
  }

  /**
   * Init.
   */
  @PostConstruct
  protected void init()  {
    final var queueName = this.configuration.getQueueName();
    log.info("Rabbit Listener start on queue '{}'", queueName);
    this.receive();
  }

  /**
   * Handle message.
   *
   * @param delivery the delivery
   */
  protected abstract void handleMessage(Delivery delivery);

  /**
   * Close.
   *
   * @throws Exception the exception
   */
  @PreDestroy
  public void close() throws Exception {
    Objects.requireNonNull(connectionMono.block()).close();
  }
}
