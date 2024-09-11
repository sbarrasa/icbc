package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface Service<I, O> extends Function<I,O> {
    O get(I input) throws Exception ;

    @Override
    default O apply(I input) {
        try {
            return get(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <I, O> Service<I, O> of(Service<I, O> service) {
        return service;
    }
}
