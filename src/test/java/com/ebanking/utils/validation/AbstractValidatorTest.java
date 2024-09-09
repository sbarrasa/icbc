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

    validator.setData(1);
    assertDoesNotThrow(() -> validator.validate());

    validator.setData(3);
    assertThrows(RuntimeException.class, validator::validate);

  }

  @Test
  void exceptionHandlerOverride() {

    var validator = new AbstractValidator<Integer>() {

      @Override
      protected ValidatorExceptionHandler exceptionHandler(){
        return (validator) -> new RuntimeException("%s es %s pero debe ser 1".formatted(getName(), getData()));
      }
      @Override
      public Predicate<Integer> getCondition() {
        return object -> object == 1;
      }

    };

    validator.setName("número");
    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }

  @Test
  void buildExceptionOverride() {

    var validator = new AbstractValidator<Integer>() {

      @Override
      protected Exception buildException(){
        return new RuntimeException("%s no es igual a 1".formatted(getData()));
      }

      @Override
      public Predicate<Integer> getCondition() {
        return object -> object == 1;
      }

    };

    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }


}