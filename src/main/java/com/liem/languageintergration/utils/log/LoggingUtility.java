package com.liem.languageintergration.utils.log;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

@Slf4j
public class LoggingUtility {

  /**
   * Extract name of package name
   *
   * @param joinPoint represents advised method
   * @param method    process method
   * @return String package name
   */
  public static String getPackageName(ProceedingJoinPoint joinPoint, Method method) {
    return joinPoint.getTarget().getClass().getSimpleName().concat(".").concat(method.getName());
  }

  /**
   * Process object.
   *
   * @param joinPoint   represents advised method
   * @param stopWatch   process logging stop watch
   * @param packageName process package name
   * @param type        the type
   * @return process result objects
   * @throws Throwable in case of exception
   */
  public static Object process(
      ProceedingJoinPoint joinPoint, StopWatch stopWatch, String packageName, String type)
      throws Throwable {
    log.info("Start {} --- {} ---", type, packageName);
    stopWatch.start();
    final Object retVal = joinPoint.proceed();
    stopWatch.stop();
    log.info("End {} --- {} --- in [{}] ms", type, packageName, stopWatch.getTotalTimeMillis());
    return retVal;
  }

  /**
   * Extract method from join point
   *
   * @param joinPoint represents advised method
   * @return Method method
   * @throws NoSuchMethodException in case of exception
   */
  public static Method extractMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
    final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    final Class<?> targetClass = joinPoint.getTarget().getClass();
    if (Modifier.isPublic(signature.getMethod().getModifiers())) {
      return targetClass.getMethod(signature.getName(), signature.getParameterTypes());
    } else {
      return ReflectionUtils.findMethod(targetClass, signature.getName(),
          signature.getParameterTypes());
    }
  }

}
