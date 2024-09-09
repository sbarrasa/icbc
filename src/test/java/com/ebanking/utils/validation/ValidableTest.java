package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidableTest {
  @Test
  void validateTest(){

    var validable = new Validable<>() {
      public void validate() throws Exception {
        throw new Exception();
      }

      @Override
      public void validate(Object value) throws Exception {
        if(Objects.isNull(value))
            throw new Exception();
      }

    };
    assertThrows(Exception.class, validable::validate);
    assertThrows(Exception.class, () -> validable.validate(null));

  }
}
