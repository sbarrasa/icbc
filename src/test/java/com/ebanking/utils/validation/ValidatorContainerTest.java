package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorContainerTest {
  Validator<Integer> indexValidator = new Validator<>(data -> data == 10 );

  ValidatorContainer<String> validator = new ValidatorContainer<String>()
          .add(new Validator<>(Objects::nonNull))
          .add(new Validator<>(Validator::nonEmpty))
          .add(new Validator<>(object -> object.equals("Hola mundo")))
          .add(indexValidator);


  @Test
  void validateOk() {
    indexValidator.setValue(10);
    validator.setValue("Hola mundo");

    assertDoesNotThrow(() -> validator.validate());
  }

  @Test
  void validateErr() {
    indexValidator.setValue(2);
    validator.setValue("Hola mundo");
    assertThrows(RuntimeException.class, () -> validator.validate());

    validator.setValue(null);
    assertThrows(RuntimeException.class, () -> validator.validate());

    validator.setValue("    ");
    assertThrows(RuntimeException.class, () -> validator.validate());

    validator.setValue("Hola");
    assertThrows(RuntimeException.class, () -> validator.validate());
  }

  @Test
  void validatorCheckSize(){
    assertEquals(4, validator.validators().size());
  }

}