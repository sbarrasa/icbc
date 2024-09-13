package com.ebanking.utils.converter;

import java.util.function.Function;

@FunctionalInterface
public interface Transformer<I, O> extends Function<I, O>  {
    O transform(I input) throws Exception;
    default O apply(I input){
        try {
            return transform(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
