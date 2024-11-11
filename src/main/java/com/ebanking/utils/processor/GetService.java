package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface GetService<I, O> extends Function<I,O> {
    O get(I input) throws Exception ;

    @Override
    default O apply(I input) {
        try {
            return get(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
