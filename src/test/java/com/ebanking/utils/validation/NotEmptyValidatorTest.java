package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyValidatorTest {
  @Test
  void validate() {
    var validator = new Validator<String>(Validator::nonEmpty);

    assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertThrows(RuntimeException.class, () -> validator.validate(""));
    assertThrows(RuntimeException.class, () -> validator.validate("  "));
    assertDoesNotThrow(() -> validator.validate("Hola"));

  }

}