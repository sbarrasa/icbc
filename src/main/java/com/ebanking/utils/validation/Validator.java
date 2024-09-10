package com.ebanking.utils.validation;

import java.util.Objects;
import java.util.function.Predicate;

public class Validator<I> implements Validable<I> {

  public static String defaultExceptionMessage = "validation error: %s";
  public static ExceptionHandler<?> defaultExceptionHandler = input -> new Exception(defaultExceptionMessage.formatted(input));

  public static boolean nonEmpty(String string) {
    return !Objects.isNull(string)
            && !string.trim().isEmpty();
  }

  private ExceptionHandler<I> exceptionHandler;

  private final Predicate<I> condition;

  public Validator(Predicate<I> condition) {
    this.condition = condition;
  }

  public void validate(I input) throws Exception {
    if (!condition.test(input))
      throw exceptionHandler().build(input);
  }

  protected ExceptionHandler<I> exceptionHandler() {
    if (exceptionHandler == null)
      exceptionHandler = (ExceptionHandler<I>) defaultExceptionHandler;

    return this.exceptionHandler;
  }

  public Validator<I> exceptionHandler(ExceptionHandler<I> exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
    return this;
  }


}
