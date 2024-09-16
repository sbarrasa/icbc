package com.ebanking.utils.validator;

import com.ebanking.utils.types.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringSizeValidatorTest {
  static StringSizeValidator validator = new StringSizeValidator(new Range<>(2,5));

  @Test
  void validateOk(){
    assertDoesNotThrow(() -> validator.validate("12345"));
  }

  @Test
  void validateErr(){
    assertThrows(Exception.class, () -> validator.validate("A"));

    assertThrows(Exception.class, () -> validator.validate("ABCD1234"));

  }



}
