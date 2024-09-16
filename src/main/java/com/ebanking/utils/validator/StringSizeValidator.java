package com.ebanking.utils.validator;

import com.ebanking.utils.types.Range;

import java.util.function.Predicate;

public class StringSizeValidator extends Validator<String> {
    private Range<Integer> size = new Range<>(1, Integer.MAX_VALUE);

    public Range<Integer> getSize(){
        return size;
    }

    public StringSizeValidator setSize(Range<Integer> size){
        this.size = size;
        return this;
    }

    @Override
    protected Predicate<String> getCondition() {
        return value -> getSize().contains(value.length());
    }


}
