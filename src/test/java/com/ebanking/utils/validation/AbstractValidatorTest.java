package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class AbstractValidatorTest {
  private static class OneValidator extends AbstractValidator<Integer> {
    @Override
    public Predicate<Integer> getCondition() {
      return numero -> numero == 1;
    }
  }

  @Test
  void validate()  {
    var validator = new OneValidator();

    assertThrows(RuntimeException.class, () -> validator.validate(2));

    validator.setValue(1);
    assertDoesNotThrow(() -> validator.validate());

    validator.setValue(3);
    assertThrows(RuntimeException.class, validator::validate);

  }

  @Test
  void exceptionHandlerOverride() {

    var validator = new AbstractValidator<Integer>() {

      @Override
      protected ValidatorExceptionHandler exceptionHandler(){
        return (validator) -> new RuntimeException("%s no es 1".formatted(
                validator.getValue()));
      }
      @Override
      public Predicate<Integer> getCondition() {
        return object -> object == 1;
      }

    };
    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }

  @Test
  void buildExceptionOverride() {

    var validator = new AbstractValidator<Integer>() {

      @Override
      protected Exception buildException(){
        return new RuntimeException("%s no es igual a 1".formatted(getValue()));
      }

      @Override
      public Predicate<Integer> getCondition() {
        return object -> object == 1;
      }

    };

    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }


}