package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorContainerTest {
  ValidatorContainer<String> validator = new ValidatorContainer<String>()
          .add(Validator.build(Objects::nonNull))
          .add(Validator.build(Validator::nonEmpty))
          .add(Validator.build(object -> object.equals("Hola mundo")));
  @Test
  void validateOk() {
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
  }

  @Test
  void validateErr() {
    assertThrows(Exception.class, () -> validator.validate(null));
    assertThrows(Exception.class, () -> validator.validate("   "));
    assertThrows(Exception.class, () -> validator.validate("Chau"));
  }


}