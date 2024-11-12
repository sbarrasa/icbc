package com.ebanking.utils.factory;

public interface Factory<K, T> {
  T create(K key);
}
