package com.ebanking.utils.factory;

@FunctionalInterface
public interface FactorySupplier<T> {
  T create();
}
