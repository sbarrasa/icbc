package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
    assertThrows(ValidatorException.class, validator::validate);

    validator.setData(1);
    assertDoesNotThrow(() -> validator.validate());

    assertThrows(ValidatorException.class, () -> validator.validate(2));

  }

  @Test
  void exceptionFunction() {

    var validator = new AbstractValidator<Integer>() {
      @Override
      public Function<String, RuntimeException> exceptionFunction(){
        return RuntimeException::new;
      }
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

  @Test
  void messageBuilderDefault() {
    var validator = new AbstractValidator<>() {
      @Override
      public Predicate<Object> getCondition() {
        return Objects::nonNull;
      }
    };

    var ex = assertThrows(ValidatorException.class, () -> validator.validate(null));
    assertEquals(validator.messageBuilder().get(), ex.getMessage());
  }

  @Test
  void messageBuilder() {
    var message = "El valor %s es erróneo";
    var validator = new AbstractValidator<>() {
      @Override
      public Predicate<Object> getCondition() {
        return Objects::nonNull;
      }
      @Override
      public Supplier<String> messageBuilder(){
        return () -> message.formatted(getData());
      }
    };

    var ex = assertThrows(ValidatorException.class, () -> validator.validate(null));
    assertEquals(validator.messageBuilder().get(), ex.getMessage());
  }

}