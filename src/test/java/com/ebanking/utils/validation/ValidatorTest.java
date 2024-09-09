package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void condition() {
    var validator = new Validator<Integer>(object -> object == 10);
    assertThrows(ValidatorException.class, () -> validator.validate(2));
    assertDoesNotThrow(() -> validator.validate(10));
  }

  @Test
  void messageBuilder() {
    var message = "Especifique el valor";
    var validator = new Validator<>(Objects::nonNull)
            .messageBuilder(() -> message);

    var ex = assertThrows(ValidatorException.class, () -> validator.validate(null));
    assertEquals(validator.messageBuilder().get(), ex.getMessage());
  }

  @Test
  void exceptionFunction(){
    var validator = new Validator<String>(Validator::nonEmpty)
            .exceptionFunction(RuntimeException::new)
            .setName("Prueba");

    assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertEquals("Prueba", validator.getName());

  }
}
