package com.ebanking.utils.validation;

@FunctionalInterface
public interface ValidatorExceptionFunction {
    Exception apply(AbstractValidator<?> validator);
}
