package com.ebanking.utils.processor;

import java.util.function.Predicate;

public class NotEmptyValidator extends Validator<String> {

    @Override
    protected Predicate<String> getCondition() {
        return Validator.nonEmpty;
    }
}
