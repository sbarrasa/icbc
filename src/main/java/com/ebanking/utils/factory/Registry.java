package com.ebanking.utils.factory;

import com.ebanking.utils.processor.GetService;

public interface Registry<K, V> extends GetService<K, V> {
  Registry<K,V> register(K key, V value);
  Registry<K,V> unregister(K key);
}
