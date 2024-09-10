package com.ebanking.utils.processor;

import java.util.function.Function;

public interface Transformer<I, O> extends Function<I, O>  {
    O transform(I input);

    default O apply(I input){
        return transform(input);
    }
}
