package com.ebanking.utils.processor;


@FunctionalInterface
public interface Validable<I, E extends Exception>  {
  void validate(I value) throws E;

}