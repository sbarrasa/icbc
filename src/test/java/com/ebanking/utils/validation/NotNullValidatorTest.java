package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class NotNullValidatorTest {
  @Test
  void validate()  {
    var validator = new Validator<>(Objects::nonNull);

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(ValidatorException.class, () -> validator.validate(null));
  }

}