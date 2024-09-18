package com.ebanking.utils.validator;

import java.util.function.Predicate;

public abstract class Validator<I> implements Validable<I> {

  public static String defaultExceptionMessage = "value error (%s)";

  public static ExceptionHandler<?, ?> defaultExceptionHandler = (message, value) -> new RuntimeException(message.formatted(value));

  public static Predicate<String> nonEmpty = string -> string !=null
                                                    && !string.trim().isEmpty();


  public static Predicate<String> isDigits = string -> nonEmpty.test(string)
          && string.chars().allMatch(Character::isDigit);

  private String exceptionMessage = defaultExceptionMessage;
  @SuppressWarnings("unchecked")
  private ExceptionHandler<I, ?> exceptionHandler = (ExceptionHandler<I, ?>) defaultExceptionHandler;

  protected abstract Predicate<I> getCondition();

  @Override
  public void validate(I input) throws Exception {
    if (!getCondition().test(input)) {
      throw exceptionHandler.buildException(exceptionMessage, input);
    }
  }

  public String getExceptionMessage(){
    return exceptionMessage;
  }

  public Validator<I> setExceptionMessage(String exceptionMessage){
    this.exceptionMessage = exceptionMessage;
    return this;
  }

  public Validator<I> setExceptionHandler(ExceptionHandler<I, ?> exceptionHandler){
    this.exceptionHandler = exceptionHandler;
    return this;
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
