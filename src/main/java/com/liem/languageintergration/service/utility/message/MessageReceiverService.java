package com.liem.languageintergration.service.utility.message;

/**
 * The interface Message receiver service.
 *
 * @param <MESSAGE> the type parameter
 */
public interface MessageReceiverService<MESSAGE> {

  /**
   * Receive message.
   *
   * @param message the message
   * @return the message
   */
  MESSAGE receive();
}
