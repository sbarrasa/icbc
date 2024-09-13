package com.ebanking.utils.validator;

import java.util.function.Predicate;

public class DigitValidator extends Validator<String> {
    private Integer minSize = 1;

    public DigitValidator setMinSize(Integer minSize){
        this.minSize = minSize;
        return this;
    }

    public Integer getMinSize(){
        return minSize;
    }

    @Override
    protected Predicate<String> getCondition() {
        return  value -> Validator.isDigits.test(value)
                && value.length() >= minSize;
    }
}
