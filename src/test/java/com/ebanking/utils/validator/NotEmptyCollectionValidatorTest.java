package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotEmptyCollectionValidatorTest {
  static NotEmptyCollectionValidator<Exception> validator = new NotEmptyCollectionValidator<>();

  @Test
  void validateOkTest() {
    assertDoesNotThrow(() -> validator.validate(List.of("Hola mundo")));
  }

  @Test
  void validateErrTest() {
    assertThrows(Exception.class, () -> validator.validate(List.of()));
    assertThrows(Exception.class, () -> validator.validate(Set.of()));
    assertThrows(Exception.class, () -> validator.validate(null));
  }
}
