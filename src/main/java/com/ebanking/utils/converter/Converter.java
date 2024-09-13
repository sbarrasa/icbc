package com.ebanking.utils.converter;

import java.util.function.Function;

@FunctionalInterface
public interface Converter<T1, T2> extends Function<T1, T2> {
    T2 from(T1 input) throws Exception;
    default T2 apply(T1 input) {
        try {
            return from(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}