package com.ebanking.utils.validator;

import com.ebanking.utils.types.Range;

import java.util.function.Predicate;

public class StringSizeValidator extends Validator<String> {
    private final Range<Integer> size;


    public StringSizeValidator(Range<Integer> size) {
        this.size = size;
    }


    public Range<Integer> getSize(){
        return size;
    }


    @Override
    protected Predicate<String> getCondition() {
        return value -> getSize().contains(value.length());
    }

    public StringSizeValidator() {
        this(new Range<>(1, Integer.MAX_VALUE));
    }

}
