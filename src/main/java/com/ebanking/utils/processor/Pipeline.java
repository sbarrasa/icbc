package com.ebanking.utils.processor;

import java.util.function.Function;

public class Pipeline {
    public static <T> Function<T, T> start() {
        return t -> t;
    }

}