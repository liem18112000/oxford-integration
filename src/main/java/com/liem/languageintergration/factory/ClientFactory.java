package com.liem.languageintergration.factory;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * The type Client factory.
 */
@Component
public class ClientFactory {

  /**
   * Create http client.
   *
   * @param timeout the timeout
   * @return the http client
   */
  public HttpClient createHttpClient(final @NotNull Duration timeout) {
    final var millis = Math.toIntExact(timeout.toMillis());
    return HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, millis)
        .responseTimeout(timeout)
        .doOnConnected(conn ->
            conn.addHandlerLast(new ReadTimeoutHandler(millis, TimeUnit.MILLISECONDS))
                .addHandlerLast(new WriteTimeoutHandler(millis, TimeUnit.MILLISECONDS)));
  }

  /**
   * Create reactive web client.
   *
   * @param httpClient the http client
   * @return the web client
   */
  public WebClient createReactiveWebClient(final @NotNull HttpClient httpClient) {
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .build();
  }
}
