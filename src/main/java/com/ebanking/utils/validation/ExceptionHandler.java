package com.ebanking.utils.validation;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionHandler extends Function<String, Exception> {
}
