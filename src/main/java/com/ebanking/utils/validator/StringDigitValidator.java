package com.ebanking.utils.validator;

import java.util.function.Predicate;

public class StringDigitValidator extends StringSizeValidator {
    public static Predicate<String> isDigits = string -> NotEmptyValidator.nonEmpty.test(string)
        && string.chars().allMatch(Character::isDigit);

    public StringDigitValidator(){
        setExceptionHandler((message, value) -> new NumberFormatException(message.formatted(value)));
    }

    @Override
    protected Predicate<String> getCondition() {
        return super.getCondition()
                .and(value -> isDigits.test(value));
    }
}
