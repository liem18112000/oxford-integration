package com.liem.languageintergration.excpetions;

import com.liem.languageintergration.dto.oxford.EntryDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Translation exception.
 */
public class TranslationException extends ResponseStatusException {

  /**
   * The Entry.
   */
  @Getter
  private EntryDto entry;

  /**
   * Instantiates a new Translation exception.
   *
   * @param status the status
   * @param entry  the entry
   */
  public TranslationException(HttpStatus status, EntryDto entry) {
    super(status);
    this.entry = entry;
  }

  /**
   * Instantiates a new Translation exception.
   *
   * @param status the status
   * @param reason the reason
   * @param entry  the entry
   */
  public TranslationException(HttpStatus status, String reason, EntryDto entry) {
    super(status, reason);
    this.entry = entry;
  }
}
