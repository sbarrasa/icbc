package com.ebanking.utils.processor;

public interface Service<I, O> extends GetService<I,O>, SaveService<I,O> {
  @Override
  default O apply(I input) {
    return GetService.super.apply(input);
  }
}
