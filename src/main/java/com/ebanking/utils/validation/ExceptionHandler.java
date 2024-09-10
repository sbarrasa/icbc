package com.ebanking.utils.validation;

@FunctionalInterface
public interface ExceptionHandler<I> {
  Exception build(I input);
}
