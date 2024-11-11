package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotEmptyValidatorTest {
  static NotEmptyValidator<Exception> validator = new NotEmptyValidator<>();

  @Test
  void validateOkTest() {
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
  }

  @Test
  void validateErrTest() {
    assertThrows(Exception.class, () -> validator.validate(""));
    assertThrows(Exception.class, () -> validator.validate(" "));
    assertThrows(Exception.class, () -> validator.validate(null));
  }
}
