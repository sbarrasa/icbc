package com.ebanking.utils.validator;

@FunctionalInterface
public interface ExceptionHandler<V,  E extends Exception>  {
  E buildException(String message, V value);

}
