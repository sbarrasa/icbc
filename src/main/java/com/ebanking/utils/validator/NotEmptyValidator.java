package com.ebanking.utils.validator;

import java.util.function.Predicate;

public class NotEmptyValidator extends Validator<String> {
    public static Predicate<String> nonEmpty = string ->
                                                        string !=null
                                                        && !string.trim().isEmpty();


    @Override
    protected Predicate<String> getCondition() {
        return nonEmpty;
    }
}
