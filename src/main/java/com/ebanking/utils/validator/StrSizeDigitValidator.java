package com.ebanking.utils.validator;

import com.ebanking.utils.range.Range;

public class StrSizeDigitValidator<E extends Exception> extends ValidatorContainer<String, E>{
    public StrSizeDigitValidator(Range<Integer> size) {
        this(size, null);
    }


    public StrSizeDigitValidator(Range<Integer> size, ExceptionHandler<E> exceptionHandler) {
        super(exceptionHandler);
        add(new NotEmptyValidator<>());
        add(new StrDigitValidator<>());
        add(new StrSizeValidator<>(size));
    }
}
