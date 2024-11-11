package com.ebanking.utils.validator;

import com.ebanking.utils.range.Range;

import java.util.function.Predicate;

public class StrSizeValidator<E extends Exception> extends Validator<String, E> {
    private final Range<Integer> size;

    public StrSizeValidator(Range<Integer> size) {
        this.size = size;
        setExceptionMessage("El valor %s debe tener entre %d y %d caracteres");
    }

    @Override
    public String createExceptionMessage(String value){
        return getExceptionMessage().formatted(
                value,
                getSize().getMin(),
                getSize().getMax());
    }

    public Range<Integer> getSize(){
        return size;
    }

    @Override
    protected Predicate<String> getCondition() {
        return value -> getSize().contains(value.length());
    }


}
