package com.ebanking.utils.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MappedFactory<K, T> implements Factory<K, T>, Registry<K, FactorySupplier<? extends T>> {

  private static final String NO_CREATOR = "No hay un constructor para la clave %s";
  private final Map<K, FactorySupplier<? extends T>> registry;

  public MappedFactory() {
    this.registry = new HashMap<>();
  }

  public MappedFactory(Map<K, FactorySupplier<? extends T>> registry) {
    this.registry = registry;
  }


  @Override
  public T create(K key) {
    var creator = registry.get(key);
    if(Objects.isNull(creator))
      throw new IllegalArgumentException(NO_CREATOR.formatted(key));
    return creator.create();
  }

  @Override
  public MappedFactory<K, T> register(K key, FactorySupplier<? extends T> creator) {
    registry.put(key, creator);
    return this;
  }

  @Override
  public Registry<K, FactorySupplier<? extends T>> unregister(K key) {
    registry.remove(key);
    return this;
  }


}