package com.ebanking.utils.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComponentFactoryTest {
  @Test
  void create(){

    var factory = new ComponentFactory<Number>()
            .register(Integer.class, () -> 5+5)
            .register(Double.class, () -> 1.5)
            .register(Long.class, ()-> 1L);

    assertEquals(10, factory.create(Integer.class));
    assertEquals(1.5, factory.create(Double.class));
    assertEquals(1L, factory.create(Long.class));


  }
}
