package com.ebanking.utils.validator;

import com.ebanking.utils.range.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrSizeValidatorTest {
  static StrSizeValidator<?> validator = new StrSizeValidator<>(new Range<>(2,5));

  @Test
  void validateOkTest(){
    assertDoesNotThrow(() -> validator.validate("12345"));
  }

  @Test
  void validateErrTest(){
    assertThrows(Exception.class, () -> validator.validate("A"));
    assertThrows(Exception.class, () -> validator.validate("ABCD1234"));
  }



}
