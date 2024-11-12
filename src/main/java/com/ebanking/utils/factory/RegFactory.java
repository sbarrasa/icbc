package com.ebanking.utils.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class RegFactory<K, T> implements Factory<K, T>, Registry<K, Supplier<? extends T>> {

  private static final String NO_CREATOR = "No hay un constructor para la clave %s";
  private final Map<K, Supplier<? extends T>> registry;

  public RegFactory() {
    this.registry = new HashMap<>();
  }

  public RegFactory(Map<K, Supplier<? extends T>> registry) {
    this.registry = registry;
  }


  @Override
  public T create(K key) {
    var supplier = registry.get(key);
    if(Objects.isNull(supplier))
      throw new IllegalArgumentException(NO_CREATOR.formatted(key));
    return supplier.get();
  }

  @Override
  public RegFactory<K, T> register(K key, Supplier<? extends T> creator) {
    registry.put(key, creator);
    return this;
  }

  @Override
  public Registry<K, Supplier<? extends T>> unregister(K key) {
    registry.remove(key);
    return this;
  }


}