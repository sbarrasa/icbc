package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotNullValidatorTest {
  static NotNullValidator validator = new NotNullValidator();
  @Test
  void validateOk() {
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
  }
  @Test
  void validateErr() {
    assertThrows(Exception.class, () -> validator.validate(null));
  }
}
