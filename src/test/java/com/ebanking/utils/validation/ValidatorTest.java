package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void condition() {
    var validator = new Validator<Integer>(object -> object == 10);
    assertThrows(RuntimeException.class, () -> validator.validate(2));
    assertDoesNotThrow(() -> validator.validate(10));
  }


  @Test
  void exceptionFunction(){
    var validator = new Validator<String>(Validator::nonEmpty)
            .exceptionFunction(data -> new RuntimeException("Error"))
            .setName("Prueba");

    assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertEquals("Prueba", validator.getName());

  }
}
