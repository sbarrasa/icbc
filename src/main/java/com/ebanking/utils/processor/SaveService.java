package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface SaveService<I, O> extends Function<I,O> {
    O save(I input) throws Exception ;

    @Override
    default O apply(I input) {
        try {
            return save(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
