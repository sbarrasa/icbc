package com.ebanking.utils.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class MappedFactory<K, T> implements Factory<K, T>, Registry<K, Supplier<? extends T>> {

  private static final String NO_CREATOR = "No hay un constructor para la clave %s";
  private final Map<K, Supplier<? extends T>> registry;

  public MappedFactory() {
    this.registry = new HashMap<>();
  }

  public MappedFactory(Map<K, Supplier<? extends T>> registry) {
    this.registry = registry;
  }


  @Override
  public T create(K key) {
    var creator = registry.get(key);
    if(Objects.isNull(creator))
      throw new IllegalArgumentException(NO_CREATOR.formatted(key));
    return creator.get();
  }

  @Override
  public MappedFactory<K, T> register(K key, Supplier<? extends T> creator) {
    registry.put(key, creator);
    return this;
  }

  @Override
  public Registry<K, Supplier<? extends T>> unregister(K key) {
    registry.remove(key);
    return this;
  }

  @Override
  public Supplier<? extends T> get(K key) {
    return registry.get(key);
  }
}