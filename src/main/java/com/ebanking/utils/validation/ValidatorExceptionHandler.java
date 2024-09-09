package com.ebanking.utils.validation;

@FunctionalInterface
public interface ValidatorExceptionHandler {
  Exception build(AbstractValidator<?> validable);
}
