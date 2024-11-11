package com.ebanking.utils.factory;

import java.util.function.Supplier;

public class ComponentFactory<T> implements Factory<T>, Registry<Class<? extends T>, Supplier<? extends T>> {

  private final RegistryMap<Class<? extends T>, Supplier<? extends T>> registryMap;

  public ComponentFactory() {
    this.registryMap = new RegistryMap<>();
  }

  public ComponentFactory(RegistryMap<Class<? extends T>, Supplier<? extends T>> registryMap) {
    this.registryMap = registryMap;
  }

  @Override
  public ComponentFactory<T> register(Class<? extends T> key, Supplier<? extends T> creator) {
    registryMap.register(key, creator);
    return this;
  }

  @Override
  public ComponentFactory<T> unRegister(Class<? extends T> key) {
    registryMap.unRegister(key);
    return this;
  }

  @Override
  public <S extends T> S create(Class<S> keyClass) {
    @SuppressWarnings("unchecked")
    Supplier<S> creator = (Supplier<S>) registryMap.get(keyClass);
    return creator.get();
  }

  @Override
  public Supplier<? extends T> get(Class<? extends T> key) {
    return registryMap.get(key);
  }
}