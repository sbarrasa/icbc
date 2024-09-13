package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface Converter<I, O> extends Function<I, O> {
    O convert(I input) throws Exception;
    default O apply(I input) {
        try {
            return convert(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}