package com.ebanking.utils.validation;

import java.util.function.Function;

public interface Service<I, O> extends Function<I,O> {
    O get(I input);

    @Override
    default O apply(I i) {
        return get(i);
    }
}
