package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
  @Test
  void validateCondition() {
    String message = "%s es inválido";

    var validator = new Validator<Integer>(numero -> numero >= 10)
            .exceptionHandler(value -> new Exception(message.formatted(value)));

    assertDoesNotThrow(() -> validator.validate(10));
    var ex = assertThrows(Exception.class, () -> validator.validate(2));
    assertEquals(message.formatted(2), ex.getMessage());
  }

  @Test
  void validateNotNull()  {
    var validator = new Validator<>(Objects::nonNull);

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(RuntimeException.class, () -> validator.validate(null));
  }

  @Test
  void validateNotEmpty()  {
    var validator = new Validator<String>(Validator::nonEmpty);

    assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertThrows(RuntimeException.class, () -> validator.validate(""));
    assertThrows(RuntimeException.class, () -> validator.validate("  "));
    assertDoesNotThrow(() -> validator.validate("Hola"));

  }

}
