package com.ebanking.utils.processor;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class Validator<I> implements Validable<I> {

  public static String defaultExceptionMessage = "value error (%s)";

  public static ExceptionHandler defaultExceptionHandler = Exception::new;

  public static Predicate<String> noNull = Objects::nonNull;

  public static Predicate<String> nonEmpty = string -> noNull.test(string)
                                                    && !string.trim().isEmpty();


  private ExceptionHandler exceptionHandler;

  public static Predicate<String> isDigits = string -> nonEmpty.test(string)
          && string.chars().allMatch(Character::isDigit);

  private String exceptionMessage = defaultExceptionMessage;


  protected abstract Predicate<I> getCondition();

  @Override
  public void validate(I input) throws Exception {
    if (!getCondition().test(input)) {
      throw getExceptionHandler().apply(exceptionMessage.formatted(input));
    }
  }


  public ExceptionHandler getExceptionHandler() {
    if(exceptionHandler == null)
      exceptionHandler=defaultExceptionHandler;

    return this.exceptionHandler;
  }

  public Validator<I> setExceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
    return this;
  }

  public Validator<I> setExceptionMessage(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
    return this;
  }

  public String getExceptionMessage() {
    return  this.exceptionMessage;
  }

  public static <I> Validator<I> build(Predicate<I> condition){
      return new Validator<>() {
        @Override
        protected Predicate<I> getCondition() {
          return condition;
        }
      };
  }

}
