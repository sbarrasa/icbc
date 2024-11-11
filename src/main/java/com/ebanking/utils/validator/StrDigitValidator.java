package com.ebanking.utils.validator;

import java.util.function.Predicate;

public class StrDigitValidator<E extends Exception> extends Validator<String, E> {
    public static Predicate<String> isDigits = string -> NotEmptyValidator.nonEmpty.test(string)
            && string.chars().allMatch(Character::isDigit);

    public StrDigitValidator(){
        setExceptionMessage("El valor %s no está compuesto por números");
    }

    @Override
    protected Predicate<String> getCondition() {
        return value -> isDigits.test(value);
    }
}
