package com.ebanking.utils.validator;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionHandler extends Function<String, Exception> {

}
