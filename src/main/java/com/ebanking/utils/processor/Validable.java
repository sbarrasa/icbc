package com.ebanking.utils.processor;


import java.util.function.Function;

public interface Validable<I> extends Function<I, I> {
  void validate(I value) throws Exception;

  @Override
  default I apply(I value) {
    try {
      validate(value);
      return value;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}