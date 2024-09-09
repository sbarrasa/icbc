package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyValidatorTest {
  @Test
  void validate() {
    var validator = new Validator<String>(Validator::nonEmpty);

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(ValidatorException.class, () -> validator.validate(""));
    assertThrows(ValidatorException.class, () -> validator.validate("  "));
    assertThrows(ValidatorException.class, () -> validator.validate(null));

  }

}