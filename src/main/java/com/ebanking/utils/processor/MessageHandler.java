package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface MessageHandler<T> extends Function<T, String> {
}
