package com.ebanking.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CompositeValidatorTest {
  CompositeValidator<String> validator;

  @BeforeEach
  void buildValidator(){
    validator = new CompositeValidator<String>()
            .add(new Validator<>(Objects::nonNull))
            .add(new Validator<>(Validator::nonEmpty))
            .add(new Validator<>(object -> object.equals("Hola mundo")))
            .add(new Validator<Integer>(object -> true ));

  }

  @Test
  void validateOk() {
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
    assertNull(validator.getValidatorFail());

  }

  @Test
  void validateErr() {
    assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertEquals(Validator.class, validator.getValidatorFail().getClass());
    assertThrows(RuntimeException.class, () -> validator.validate("   "));
    assertThrows(RuntimeException.class, () -> validator.validate("Hola"));
  }

  @Test
  void validatorCheckSize(){
    assertEquals(4, validator.validators().size());
  }

}