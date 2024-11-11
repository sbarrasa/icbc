package com.ebanking.utils.validator;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class NotEmptyCollectionValidator<E extends Exception> extends Validator<Collection<?>, E> {
    public static final Predicate<Collection<?>> nonEmpty = collection ->
            Objects.nonNull(collection)
                    && !collection.isEmpty();

    @Override
    protected Predicate<Collection<?>> getCondition() {
        return nonEmpty;
    }
}