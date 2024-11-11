package com.ebanking.utils.factory;

public interface Factory<T> {
  <S extends T> S create(Class<S> keyClass);
}
