package com.ebanking.utils.validation;


public interface Validable<T> {
  void validate(T value) throws Exception;
}