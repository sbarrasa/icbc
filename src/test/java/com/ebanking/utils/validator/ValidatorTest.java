package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;


import java.util.InputMismatchException;
import java.util.Objects;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void abstractCreateTest() {

    Validator<Integer, Exception> naturales = new Validator<>() {
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
  void builderTest() {

    var validator = Validator.<String, Exception>of("Hola"::equals)
            .setExceptionMessage("%s debe ser Hola");

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(Exception.class, () -> validator.validate(""));
    var ex = assertThrows(Exception.class, () -> validator.validate("Chau"));
    assertEquals(validator.getExceptionMessage().formatted("Chau"), ex.getMessage());

  }


  @Test
  void changeExceptionDefault(){

    var validator = Validator.of(Objects::nonNull);
    assertThrows(Exception.class, () -> validator.validate(null));

    Validator.defaultExceptionMessage = "PRUEBA";
    Validator.defaultExceptionHandler = InputMismatchException::new;

    Validator<String, Exception> validator2 = Validator.of(value -> value.equals("Hola"));

    var ex = assertThrows(InputMismatchException.class, () -> validator2.validate("Mundo"));
    assertEquals("PRUEBA", ex.getMessage());

  }

  @Test
  void changeExceptionMessage(){

    var exceptionMessage = "Error personalizado %s";
    var validator = Validator.of(Objects::nonNull);
    validator.setExceptionMessage(exceptionMessage);
  
    var ex = assertThrows(Exception.class, () -> validator.validate(null));
    assertEquals(exceptionMessage.formatted((Object) null), ex.getMessage());

  }

  @Test
  void changeExceptionHandler(){

    var exceptionMessage = "Error personalizado %s";
    var validator = Validator.of(Objects::nonNull)
        .setExceptionHandler(RuntimeException::new)
        .setExceptionMessage(exceptionMessage);

    var ex = assertThrows(RuntimeException.class, () -> validator.validate(null));
    assertEquals(exceptionMessage.formatted((Object) null), ex.getMessage());

  }


}
