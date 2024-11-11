package com.ebanking.utils.factory;

public interface Registry<K, T> extends Register<K, T>{
  T get(K key);
}
