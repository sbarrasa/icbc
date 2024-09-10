package com.ebanking.utils.validation;

import java.util.function.Predicate;

public class Processor<I, R, O> {
    private final Transformer<I,R> transformer;
    private final Service<R,O> service;
    private final Validable<O> validator;

    public Processor(Transformer<I, R> transformer,
                     Service<R,O> service,
                     Validable<O> validator){
        this.transformer = transformer;
        this.service = service;
        this.validator = validator;
    }

    public Processor(Transformer<I, R> transformer,
                     Service<R,O> service,
                     Predicate<O> condition){

        this(transformer, service, new Validator<>(condition));
    }

    public O execute(I input) throws Exception {
        var request = transformer.transform(input);
        var output = service.get(request);
        validator.validate(output);
        return output;
    }


}
