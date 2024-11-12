package com.ebanking.utils.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FactoryTest {
  private static class SwitchFactory implements Factory<String, Integer> {
    @Override
    public Integer create(String key) {
      switch (key) {
        case "uno":
          return 1;
        case "dos":
          return 2;
        default:
          return null;
      }
    }
 }

  @Test
  void testCreate(){
    var factory = new SwitchFactory();

    assertEquals(1, factory.create("uno"));
    assertEquals(2, factory.create("dos"));
    assertNull(factory.create("hola"));

  }
}
