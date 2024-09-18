package com.ebanking.utils.validator;

@FunctionalInterface
public interface ExceptionHandler<V,  E extends Exception>  {
  E createException(String message, V value);


}
