package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ContainerValidatorTest {
  Validator<Integer> indexValidator = new Validator<>(data -> data == 10 );

  ContainerValidator<String> validator = new ContainerValidator<String>()
          .add(new Validator<>(Objects::nonNull))
          .add(new Validator<>(Validator::nonEmpty))
          .add(new Validator<>(object -> object.equals("Hola mundo")))
          .add(indexValidator);


  @Test
  void validateOk() {
    indexValidator.setData(10);
    validator.setData("Hola mundo");

    assertDoesNotThrow(() -> validator.validate());
  }

  @Test
  void validateErr() {
    indexValidator.setData(2);
    validator.setData("Hola mundo");
    assertThrows(RuntimeException.class, () -> validator.validate());

    validator.setData(null);
    assertThrows(RuntimeException.class, () -> validator.validate());

    validator.setData("    ");
    assertThrows(RuntimeException.class, () -> validator.validate());

    validator.setData("Hola");
    assertThrows(RuntimeException.class, () -> validator.validate());
  }

  @Test
  void validatorCheckSize(){
    assertEquals(4, validator.validators().size());
  }

}