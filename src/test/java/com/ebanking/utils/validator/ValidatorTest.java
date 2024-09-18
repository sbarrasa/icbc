package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;


import java.util.InputMismatchException;
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

    var validator = Validator.<String>build(value -> "Hola".equals(value))
            .setExceptionMessage("%s debe ser Hola");

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(Exception.class, () -> validator.validate(""));
    var ex = assertThrows(Exception.class, () -> validator.validate("Chau"));
    assertEquals(validator.getExceptionMessage().formatted("Chau"), ex.getMessage());

  }


  @Test
  void changeExceptionDefault(){

    var validator = Validator.build(Objects::nonNull);
    assertThrows(Exception.class, () -> validator.validate(null));

    Validator.defaultExceptionMessage = "PRUEBA";
    Validator.defaultExceptionHandler = (message, value) -> new InputMismatchException(message.formatted(value));

    Validator<String> validator2 = Validator.build(value -> value.equals("Hola"));

    var ex = assertThrows(InputMismatchException.class, () -> validator2.validate("Mundo"));
    assertEquals("PRUEBA", ex.getMessage());

  }

  @Test
  void changeExceptionMessage(){

    var exceptionMessage = "Error personalizado %s";
    var validator = Validator.build(Objects::nonNull);
    validator.setExceptionMessage(exceptionMessage);
  
    var ex = assertThrows(Exception.class, () -> validator.validate(null));
    assertEquals(exceptionMessage.formatted((Object) null), ex.getMessage());

  }

  @Test
  void changeExceptionHandler(){

    var exceptionMessage = "Error personalizado %s";
    var validator = Validator.build(Objects::nonNull)
        .setExceptionHandler((message, value)-> new RuntimeException(message.formatted(value)))
        .setExceptionMessage(exceptionMessage);

    var ex = assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertEquals(exceptionMessage.formatted((Object) null), ex.getMessage());

  }


}
