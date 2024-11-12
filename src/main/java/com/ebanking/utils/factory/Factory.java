package com.ebanking.utils.factory;


@FunctionalInterface
public interface Factory<K, T> {
  T create(K key);
}
