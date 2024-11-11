package com.ebanking.utils.validator;

import com.ebanking.utils.processor.Validable;

import java.util.function.Predicate;

public abstract class Validator<I,E extends Exception> implements Validable<I,E> {

  public static String defaultExceptionMessage = "value error (%s)";

  public static ExceptionHandler<?> defaultExceptionHandler = RuntimeException::new;

  private String exceptionMessage = defaultExceptionMessage;

  private ExceptionHandler<E> exceptionHandler = (ExceptionHandler<E>) defaultExceptionHandler;

  protected abstract Predicate<I> getCondition();

  @Override
  public void validate(I input) throws E {
    if (!getCondition().test(input)) {
      throw exceptionHandler.createException(createExceptionMessage(input));
    }
  }

  public String createExceptionMessage(I value){
    return getExceptionMessage().formatted(value);
  }

  public String getExceptionMessage(){
    return exceptionMessage;
  }

  public Validator<I, E> setExceptionMessage(String exceptionMessage){
    this.exceptionMessage = exceptionMessage;
    return this;
  }

  public Validator<I, E> setExceptionHandler(ExceptionHandler<E> exceptionHandler){
    this.exceptionHandler = exceptionHandler;
    return this;
  }

  public static <I, E extends Exception> Validator<I,E> of(Predicate<I> condition){
    return new Validator<>() {
      @Override
      protected Predicate<I> getCondition() {
        return condition;
      }
    };
  }

  public static <I, E extends Exception> Validator<I, E> of(Predicate<I> condition, ExceptionHandler<E> exceptionHandler){
    Validator<I, E> validator = new Validator<>() {
      @Override
      protected Predicate<I> getCondition() {
        return condition;
      }
    };
    validator.setExceptionHandler(exceptionHandler);
    return validator;
  }

}
