package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AbstractValidatorTest {
  private static class OneValidator extends AbstractValidator<Integer> {
    @Override
    public void validate() throws Exception {
      if(Objects.isNull(getValue()))
        throw new Exception("El dato no puede ser nulo");
    }
  }

  @Test
  void validate()  {
    var validator = new OneValidator();

    assertThrows(Exception.class, () -> validator.validate(null));

    validator.setValue(1);
    assertDoesNotThrow(() -> validator.validate());

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
      public void validate() {
        if(getValue() != 1)
          throw new RuntimeException("El dato debe ser 1");
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
      public void validate() throws Exception {
        if(getValue() != 1)
          throw buildException();
      }

    };

    assertThrows(RuntimeException.class, () -> validator.validate(2));
  }


}