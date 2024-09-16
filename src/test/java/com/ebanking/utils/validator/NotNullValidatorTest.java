package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotNullValidatorTest {
  @Test
  void validateOk() {
    var validator = new NotNullValidator();
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
  }
  @Test
  void validateErr() {
    var validator = new NotNullValidator();
    assertThrows(Exception.class, () -> validator.validate(null));
  }
}
