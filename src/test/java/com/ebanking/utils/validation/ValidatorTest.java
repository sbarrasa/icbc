package com.ebanking.utils.validation;

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
    var mensaje = "%s debe ser Hola";

    var validator = Validator.builder()
            .exceptionMessageHandler(mensaje::formatted)
            .exceptionHandler(RuntimeException::new)
            .condition("Hola"::equals)
             .build();

    assertDoesNotThrow(() -> validator.validate("Hola"));
    assertThrows(RuntimeException.class, () -> validator.validate(""));
    var ex = assertThrows(RuntimeException.class, () -> validator.validate("Chau"));
    assertEquals(mensaje.formatted("Chau"), ex.getMessage());

  }


}
