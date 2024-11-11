package com.ebanking.utils.factory;

import java.util.HashMap;
import java.util.Map;

public class RegistryMap<K, V> implements Registry<K, V> {
  private final Map<K, V> registryMap = new HashMap<>();

  @Override
  public Registry<K,V> register(K key, V value) {
    registryMap.put(key, value);
    return this;
  }

  @Override
  public Register<K, V> unRegister(K key) {
    registryMap.remove(key);
    return this;
  }

  @Override
  public V get(K key) {
    V value = registryMap.get(key);
    if (value == null) {
      throw new IllegalArgumentException("No value registered for key: " + key);
    }
    return value;
  }
}