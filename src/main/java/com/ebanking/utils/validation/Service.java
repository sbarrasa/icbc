package com.ebanking.utils.validation;

public interface Service<I, O> {
    O get(I input);
}
