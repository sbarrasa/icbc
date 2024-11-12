package com.ebanking.utils.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class RegFactory<K, T> implements Factory<K, T> {

  private static final String NO_CREATOR = "No hay un constructor registrado para la clave %s";
  private final Map<K, Supplier<? extends T>> registry;

  public RegFactory() {
    this(new HashMap<>());
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

  public Map<K, Supplier<? extends T>> registry(){
    return registry;
  }

}