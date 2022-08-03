package com.liem.languageintergration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * The type Oxford application.
 */
@EnableReactiveMongoRepositories
@SpringBootApplication
public class OxfordApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(OxfordApplication.class, args);
  }

}
