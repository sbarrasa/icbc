package com.ebanking.utils.types;

@FunctionalInterface
public interface MessageBuilder<V> {
  String build(String message, V value);
}