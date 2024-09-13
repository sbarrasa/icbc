package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;


import java.util.Objects;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void abstractCreate() {

    Validator<Integer> naturales = new Validator<>() {
      @Override
      protected Predicate<Integer> getCondition() {
        return numero -> Objects.nonNull(numero)
                && numero >= 0;
      }
    };

    assertThrows(Exception.class, () -> naturales.validate(-1));
    assertDoesNotThrow(() -> naturales.validate(10));
  }

  @Test
  void builder() {

    var validator = Validator.<String>build("Hola"::equals)
            .setExceptionMessage("%s debe ser Hola")
            .setExceptionHandler(RuntimeException::new);

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(RuntimeException.class, () -> validator.validate(""));
    var ex = assertThrows(RuntimeException.class, () -> validator.validate("Chau"));
    assertEquals(validator.getExceptionMessage().formatted("Chau"), ex.getMessage());

  }

  @Test
  void notEmpty(){
    Validator<String> validator = Validator.build(Validator.nonEmpty);
    assertThrows(Exception.class, () -> validator.validate(""));
    assertThrows(Exception.class, () -> validator.validate(" "));
    assertThrows(Exception.class, () -> validator.validate(null));
    assertDoesNotThrow(() -> validator.validate("Hola mundo"));
  }


  @Test
  void changeExceptionDefault(){

    var validator = Validator.build(Objects::nonNull);
    assertThrows(Exception.class, () -> validator.validate(null));

    Validator.defaultExceptionMessage = "PRUEBA";
    Validator.defaultExceptionHandler = RuntimeException::new;
    Validator<String> validator2 = Validator.build(value -> value.equals("Hola"));

    var ex = assertThrows(RuntimeException.class, () -> validator2.validate("Mundo"));
    assertEquals("PRUEBA", ex.getMessage());

  }


}
