package com.ebanking.utils.processor;


@FunctionalInterface
public interface SaveService<I, O> {
    O save(I input) throws Exception ;

}
