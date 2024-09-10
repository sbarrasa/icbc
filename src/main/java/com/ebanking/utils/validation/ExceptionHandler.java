package com.ebanking.utils.validation;

@FunctionalInterface
public interface ExceptionHandler<T> {
  Exception build(T source);
}
