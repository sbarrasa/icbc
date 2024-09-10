package com.ebanking.utils.processor;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionHandler extends Function<String, Exception> {
}
