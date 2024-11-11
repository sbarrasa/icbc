package com.ebanking.utils.validator;

import com.ebanking.utils.fiscal.*;
import com.ebanking.utils.range.OutOfRangeException;
import com.ebanking.utils.range.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;


class ValidatorContainerTest {

    @Test
    void testAddValidator() {
        Validator<String, RuntimeException> validator = Validator.of(Objects::nonNull);
        ValidatorContainer<String, RuntimeException> validatorContainer = new ValidatorContainer<>(RuntimeException::new);
        validatorContainer.add("testValidator", validator);
        Assertions.assertEquals(validator, validatorContainer.get("testValidator"));
    }

    @Test
    void testAddValidatorWithoutKey() {
        ValidatorContainer<String, ?> validatorContainer = new ValidatorContainer<>(Exception::new);
        validatorContainer.add("Validador1", Validator.of("hola"::equals));
        validatorContainer.add(new NotEmptyValidator<>());

        assertNotNull(validatorContainer.get("Validador1"));
        assertNotNull(validatorContainer.get("NotEmptyValidator"));
        assertNull(validatorContainer.get("Hola"));

    }

    @Test
    void testValidateAllValidators() {
        ValidatorContainer<String, Exception> validatorContainer = new ValidatorContainer<>();

        validatorContainer.add("validator1", new StrDigitValidator<>());
        validatorContainer.add("validator2", new StrSizeValidator<>(new Range<>(1,2)));

        assertThrows(Exception.class, () -> validatorContainer.validate("AAAA"));
        assertEquals("validator1", validatorContainer.getValidatorKeyNotPassed());

        assertThrows(Exception.class, () -> validatorContainer.validate("123"));
        assertEquals("validator2", validatorContainer.getValidatorKeyNotPassed());

        assertDoesNotThrow(() -> validatorContainer.validate("12"));
    }

    @Test
    void testValidateWithDiferentExceptions() {
        ValidatorContainer<String, Exception> validatorContainer = new ValidatorContainer<>();

        validatorContainer.add("validator1", new StrDigitValidator<>().setExceptionHandler(FiscalException::new));
        validatorContainer.add("validator2", new StrSizeValidator<>(new Range<>(1,2)).setExceptionHandler(OutOfRangeException::new));

        assertThrows(FiscalException.class, () -> validatorContainer.validate("AAAA"));
        assertEquals("validator1", validatorContainer.getValidatorKeyNotPassed());

        assertThrows(OutOfRangeException.class, () -> validatorContainer.validate("123"));
        assertEquals("validator2", validatorContainer.getValidatorKeyNotPassed());

        assertDoesNotThrow(() -> validatorContainer.validate("12"));
    }
}