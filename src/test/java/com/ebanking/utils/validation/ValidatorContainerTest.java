package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorContainerTest {
  ValidatorContainer<String> validator = new ValidatorContainer<String>()
          .add(new Validator<>(Objects::nonNull))
          .add(new Validator<>(Validator::nonEmpty))
          .add(new Validator<>(object -> object.equals("Hola mundo")));

  @Test
  void validateOk() {
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
  }

  @Test
  void validateErr() {
    assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertThrows(RuntimeException.class, () -> validator.validate("   "));
    assertThrows(RuntimeException.class, () -> validator.validate("Chau"));
  }


}