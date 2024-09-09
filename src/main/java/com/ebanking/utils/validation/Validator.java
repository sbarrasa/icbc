package com.ebanking.utils.validation;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Validator<T> extends AbstractValidator<T> {
  public static boolean nonEmpty(String string) {
    return !Objects.isNull(string)
            && !string.trim().isEmpty();
  }

  private final Predicate<T> condition;

  private Supplier<String> messageBuilder;

  public Validator(Predicate<T> condition) {
    this.condition = condition;
  }

  @Override
  public Predicate<T> getCondition() {
    return this.condition;
  }

  public Validator<T> messageBuilder(Supplier<String> messageBuilder){
    this.messageBuilder = messageBuilder;
    return this;
  }

  public Supplier<String> messageBuilder() {
    if (messageBuilder == null )
      messageBuilder = super.messageBuilder();

    return messageBuilder;
  }

}