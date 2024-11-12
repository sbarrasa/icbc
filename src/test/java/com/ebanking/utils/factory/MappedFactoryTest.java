package com.ebanking.utils.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MappedFactoryTest {
  private MappedFactory<String, Number> factory = new MappedFactory<>();

  @BeforeEach
  void setUp() {
    factory
        .register("uno", () -> 1)
        .register("uno coma cinco", () -> 1.5)
        .register("uno longo", () -> 1L);

  }

  @Test
  void testCreateWithValidKey() {
    assertEquals(1, factory.create("uno"));
    assertEquals(1.5, factory.create("uno coma cinco"));
    assertEquals(1L, factory.create("uno longo"));

  }

  @Test
  void testCreateWithInvalidKey() {
    assertThrows(IllegalArgumentException.class, () -> factory.create("X"));
  }

  @Test
  void testRegister() {
    factory.register("X", () -> 666);
    factory.unregister("uno");

    assertEquals(666, factory.create("X"));
    assertThrows(IllegalArgumentException.class, () -> factory.create("uno"));


  }
}
