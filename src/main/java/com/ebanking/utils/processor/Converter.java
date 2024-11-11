package com.ebanking.utils.processor;


@FunctionalInterface
public interface Converter<I, O> {
    O convert(I input) throws Exception;
}