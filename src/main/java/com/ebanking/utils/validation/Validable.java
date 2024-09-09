package com.ebanking.utils.validation;


public interface Validable<T> {
  void validate() throws Exception;

  void validate(T value) throws Exception;

}