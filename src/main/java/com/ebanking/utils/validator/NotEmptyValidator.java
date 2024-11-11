package com.ebanking.utils.validator;

import java.util.function.Predicate;

public class NotEmptyValidator<E extends Exception> extends Validator<String, E> {
    public static final Predicate<String> nonEmpty = string ->
                                                        string !=null
                                                        && !string.trim().isEmpty();


    @Override
    protected Predicate<String> getCondition() {
        return nonEmpty;
    }
}
