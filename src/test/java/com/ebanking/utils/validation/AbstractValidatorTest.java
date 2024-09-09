package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class AbstractValidatorTest {


  @Test
  void validate()  {
    var validator = new AbstractValidator<Integer>() {
      @Override
      public Predicate<Integer> getCondition() {
        return numero -> numero == 1;
      }
    };

    validator.setData(2);
    assertThrows(RuntimeException.class, validator::validate);

    validator.setData(1);
    assertDoesNotThrow(() -> validator.validate());

    assertThrows(RuntimeException.class, () -> validator.validate(2));

  }

  @Test
  void exceptionFunctionOverride() {

    var validator = new AbstractValidator<Integer>() {
      @Override
      protected ValidatorExceptionFunction exceptionFunction(){
        return validator -> new RuntimeException("El valor debe ser 1");
      }
      @Override
      public Predicate<Integer> getCondition() {
        return object -> object == 1;
      }

    };

    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }

  @Test
  void exceptionFunctionTest() {

    var validator = new AbstractValidator<Integer>() {
      @Override
      public Predicate<Integer> getCondition() {
        return object -> object == 1;
      }

    };

    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }

  @Test
  void assign() {
    var validator1 = new Validator<String>(object -> true)
            .setName("validador1")
            .setData("Hola");

    var validator2 = new Validator<String>(object -> true)
            .setName("validador2");

    assertNotEquals(validator2.getName(), validator1.getName());
    assertNotEquals(validator2.getData(), validator1.getData());

    validator2.assign(validator1);

    assertEquals(validator2.getName(), validator1.getName());
    assertEquals(validator2.getData(), validator1.getData());

  }


}