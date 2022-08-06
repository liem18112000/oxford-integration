package com.liem.languageintergration.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@Configuration
public class RabbitConfiguration {

  @Getter
  @Value("${spring.rabbitmq.queue}")
  private String queueName;

  @Getter
  @Value("${spring.rabbitmq.exchange}")
  private String exchangeName;

  @Getter
  @Value("${spring.rabbitmq.routing-key}")
  private String routingKey;

  @Bean
  Mono<Connection> connectionMono() {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.useNio();
    return Mono.fromCallable(() -> connectionFactory
        .newConnection("reactor-rabbit")).cache();
  }

  @Bean
  public SenderOptions senderOptions(Mono<Connection> connectionMono) {
    return new SenderOptions()
        .connectionMono(connectionMono)
        .resourceManagementScheduler(Schedulers.boundedElastic());
  }

  @Bean
  public Sender sender(SenderOptions senderOptions) {
    return RabbitFlux.createSender(senderOptions);
  }

  @Bean
  public ReceiverOptions receiverOptions(Mono<Connection> connectionMono) {
    return new ReceiverOptions().connectionMono(connectionMono);
  }

  @Bean
  public Receiver receiver(ReceiverOptions receiverOptions) {
    return RabbitFlux.createReceiver(receiverOptions);
  }

}
