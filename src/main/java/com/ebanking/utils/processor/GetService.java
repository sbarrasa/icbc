package com.ebanking.utils.processor;


@FunctionalInterface
public interface GetService<I, O> {
    O get(I input) throws Exception ;


}
