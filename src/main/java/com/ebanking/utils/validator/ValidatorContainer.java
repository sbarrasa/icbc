package com.ebanking.utils.validator;

import com.ebanking.utils.processor.Validable;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidatorContainer<I, E extends Exception> implements Validable<I, E> {

    private final Map<String, Validator<I, E>> map = new LinkedHashMap<>();

    private final ExceptionHandler<E> exceptionHandler;

    public String getValidatorKeyNotPassed() {
        return validatorKeyNotPassed;
    }

    private String validatorKeyNotPassed;

    public ValidatorContainer(){
        this(null);
    }
    public ValidatorContainer(ExceptionHandler<E> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public ValidatorContainer<I, E> add(Validator<I,E> validator){
        return add(validator.getClass().getSimpleName(), validator);
    }

    public ValidatorContainer<I, E> add(String key, Validator<I, E> validator) {
        if(exceptionHandler!=null)
           validator.setExceptionHandler(exceptionHandler);

        map.put(key, validator);
        return this;
    }


    public Validator<I,E> get(String key){
        return map.get(key);
    }

    @Override
    public void validate(I value) throws E {
        for (Map.Entry<String, Validator<I, E>> entry : map.entrySet()) {
            validatorKeyNotPassed = entry.getKey();
            entry.getValue().validate(value);
        }
    }


}
