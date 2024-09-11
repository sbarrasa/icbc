package com.ebanking.utils.processor;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Validator<I> implements Validable<I> {

  public static Function<?, String> defaultExceptionMessageHandler = "value error (%s)"::formatted;

  public static ExceptionHandler defaultExceptionHandler = Exception::new;

  public static boolean nonEmpty(String string) {
    return !Objects.isNull(string)
            && !string.trim().isEmpty();
  }

  private ExceptionHandler exceptionHandler;
  private Function<I, String> exceptionMessageHandler;

  protected abstract Predicate<I> getCondition();

  public void validate(I input) throws Exception {
    if (!getCondition().test(input)) {
      String message = exceptionMessageHandler().apply(input);
      throw exceptionHandler().apply(message);
    }
  }

  @SuppressWarnings("unchecked")
  public Function<I, String> exceptionMessageHandler() {
    if(exceptionMessageHandler == null)
      exceptionMessageHandler = (Function<I, String>) defaultExceptionMessageHandler;

    return exceptionMessageHandler;
  }

  public ExceptionHandler exceptionHandler() {
    if(exceptionHandler == null)
      exceptionHandler=defaultExceptionHandler;

    return this.exceptionHandler;
  }

  public Validator<I> exceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
    return this;
  }

  public Validator<I> exceptionMessageHandler(Function<I, String> exceptionMessageHandler) {
    this.exceptionMessageHandler = exceptionMessageHandler;
    return this;
  }

  public static <I> Validator<I> build(Predicate<I> condition){
      return new Validator<I>() {
        @Override
        protected Predicate<I> getCondition() {
          return condition;
        }
      };
    }
}
