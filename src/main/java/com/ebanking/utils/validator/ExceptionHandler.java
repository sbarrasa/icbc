package com.ebanking.utils.validator;

@FunctionalInterface
public interface ExceptionHandler<E extends Exception>  {
  E createException(String message);
}

