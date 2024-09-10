package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface Converter<I, O> extends Function<I, O> {
    O convert(I input);

    default O apply(I input){
        return convert(input);
    }
}