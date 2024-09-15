package com.ebanking.utils.validator;

import java.util.function.Predicate;

public abstract class Validator<I> implements Validable<I> {

  public static String defaultExceptionMessage = "value error (%s)";

  public static ExceptionHandler defaultExceptionHandler = RuntimeException::new;

  public static Predicate<String> nonEmpty = string -> string !=null
                                                    && !string.trim().isEmpty();


  @SuppressWarnings("unchecked")
  private final ExceptionBuilder<I> exceptionBuilder = (ExceptionBuilder<I>) new ExceptionBuilder<>()
          .setMessage(defaultExceptionMessage)
          .setExceptionHandler(defaultExceptionHandler);

  public static Predicate<String> isDigits = string -> nonEmpty.test(string)
          && string.chars().allMatch(Character::isDigit);


  protected abstract Predicate<I> getCondition();

  @Override
  public void validate(I input) throws Exception {
    if (!getCondition().test(input)) {
      throw getExceptionBuilder().build(input);
    }
  }


  public ExceptionBuilder<I> getExceptionBuilder() {
    return this.exceptionBuilder;
  }

  public Validator<I> setExceptionMessage(String message){
    getExceptionBuilder().setMessage(message);
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
