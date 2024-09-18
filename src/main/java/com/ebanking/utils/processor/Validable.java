package com.ebanking.utils.processor;


import java.util.function.Function;

@FunctionalInterface
public interface Validable<I> extends Function<I, I> {
  void validate(I value) throws Exception;

  @Override
  default I apply(I value) {
    safeValidate(value);
    return value;
  }

  default void safeValidate(I value){
    try {
      validate(value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}