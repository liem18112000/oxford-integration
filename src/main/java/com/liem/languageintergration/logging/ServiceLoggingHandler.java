package com.liem.languageintergration.logging;

import static com.liem.languageintergration.utils.log.LoggingUtility.extractMethod;
import static com.liem.languageintergration.utils.log.LoggingUtility.getPackageName;
import static com.liem.languageintergration.utils.log.LoggingUtility.process;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * The type Process logging handler.
 */
@Aspect
@Component
@Slf4j
public class ServiceLoggingHandler {

  /**
   * The constant LOG_SERVICE.
   */
  private final String LOG_PATTERN = "execution(* com.liem..*Service.*(..))";

  /**
   * Advise. Logs the advised method.
   *
   * @param joinPoint represents advised method
   * @return method execution result
   * @throws Throwable in case of exception
   */
  @Around(LOG_PATTERN)
  public Object logControllerProcess(ProceedingJoinPoint joinPoint) throws Throwable {
    return process(joinPoint, new StopWatch(), getPackageName(
        joinPoint, extractMethod(joinPoint)), "Service");
  }

}