package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void condition() {

    var validator = new Validator<Integer>(numero -> numero == 10);

    assertThrows(RuntimeException.class, () -> validator.validate(2));
    assertDoesNotThrow(() -> validator.validate(10));
  }


  @Test
  void getName(){
    var validator = new Validator<String>(Validator::nonEmpty)
            .setName("Prueba");

    assertEquals("Prueba", validator.getName());

  }
}
