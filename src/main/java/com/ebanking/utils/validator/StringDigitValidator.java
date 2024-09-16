package com.ebanking.utils.validator;

import java.util.function.Predicate;

public class StringDigitValidator extends StringSizeValidator {

    public StringDigitValidator(){
        setExceptionHandler((message, value) -> new NumberFormatException(message.formatted(value)));
    }

    @Override
    protected Predicate<String> getCondition() {
        return super.getCondition()
                .and(value -> Validator.isDigits.test(value));
    }
}
