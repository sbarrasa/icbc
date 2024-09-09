package com.ebanking.utils.validation;

import java.util.Objects;
import java.util.function.Predicate;

public class Validator<T> extends AbstractValidator<T> {
  public static String defaultMessage = "%s = %s (error)";

  public static ValidatorExceptionHandler defaultexceptionHandler =
          validator -> new RuntimeException(defaultMessage);

  public static boolean nonEmpty(String string) {
    return !Objects.isNull(string)
            && !string.trim().isEmpty();
  }

  private final Predicate<T> condition;

  public Validator(Predicate<T> condition) {
    this.condition = condition;
  }

  @Override
  protected Predicate<T> getCondition() {
    return this.condition;
  }

}