package com.ebanking.utils.validation;

public interface Transformer<I, O> {
    O transform(I input);
}
