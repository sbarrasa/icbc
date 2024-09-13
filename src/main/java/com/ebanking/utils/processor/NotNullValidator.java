package com.ebanking.utils.processor;

import java.util.Objects;
import java.util.function.Predicate;

public class NotNullValidator extends Validator<Object> {

    @Override
    protected Predicate<Object> getCondition() {
        return Objects::nonNull;
    }
}
