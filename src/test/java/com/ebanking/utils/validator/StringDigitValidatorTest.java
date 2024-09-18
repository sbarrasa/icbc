package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringDigitValidatorTest {

  static StringDigitValidator validator = new StringDigitValidator();

  @Test
  void validateOk(){
    assertDoesNotThrow(() -> validator.validate("12345"));
  }

  @Test
  void validateErr(){
    assertThrows(Exception.class, () -> validator.validate("ABCD1234"));

  }



}
