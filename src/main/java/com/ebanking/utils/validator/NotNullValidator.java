package com.ebanking.utils.validator;

import java.util.Objects;
import java.util.function.Predicate;

public class NotNullValidator<E extends Exception> extends Validator<Object, E> {
    @Override
    protected Predicate<Object> getCondition() {
        return Objects::nonNull;
    }
}
