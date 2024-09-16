package com.ebanking.utils.validator;

import com.ebanking.utils.types.Range;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RangeValidatorTest {
  static RangeValidator<Integer> validator = new RangeValidator<>(new Range<>(-10, 15));
  @Test
  void validateOk(){
    assertDoesNotThrow(() -> validator.validate(12));
  }

  @Test
  void validateErr(){
    assertThrows(Exception.class, () -> validator.validate(25));

  }



}
