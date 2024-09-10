package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidableTest {
  @Test
  void validateTest(){

    var validable = new Validable<>() {

      @Override
      public void validate(Object value) throws Exception {
        if(Objects.isNull(value))
            throw new Exception();
      }

    };
    assertDoesNotThrow(() -> validable.validate(1234));
    assertThrows(Exception.class, () -> validable.validate(null));

  }

  @Test
  void validateLambda(){
    Predicate<Integer> isPositive = value -> value != null && value > 0;

    Validable<Integer> validable = (value) -> {
        if(!isPositive.test(value)) throw new Exception("EL valor debe ser mayor que 0");
    };

    assertDoesNotThrow(() -> validable.validate(1234));
    assertThrows(Exception.class, () -> validable.validate(null));
    assertThrows(Exception.class, () -> validable.validate(-1));

  }

}
