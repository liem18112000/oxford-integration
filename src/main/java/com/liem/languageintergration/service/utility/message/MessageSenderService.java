package com.liem.languageintergration.service.utility.message;

/**
 * The interface Message sender service.
 *
 * @param <MESSAGE> the type parameter
 */
public interface MessageSenderService<MESSAGE> {

  /**
   * Send message.
   *
   * @param message the message
   * @return the message
   */
  MESSAGE send(MESSAGE message);

}
