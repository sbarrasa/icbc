package com.ebanking.utils.factory;

public interface Registry<K, V>  {
  Registry<K,V> register(K key, V value);
  Registry<K,V> unregister(K key);
}
