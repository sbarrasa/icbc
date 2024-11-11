package com.ebanking.utils.factory;

public interface Register<K, V> {
  Register<K,V> register(K key, V value);

  Register<K,V> unRegister(K key);

}