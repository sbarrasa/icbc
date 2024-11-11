package com.ebanking.utils.range;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RangeValidatorTest {
  static RangeValidator<Integer> validator = new RangeValidator<>(new Range<>(-10, 15));
  @Test
  void validateOkTest(){
    assertDoesNotThrow(() -> validator.validate(2));
  }

  @Test
  void validateErrTest(){
    assertThrows(Exception.class, () -> validator.validate(25));

  }



}
