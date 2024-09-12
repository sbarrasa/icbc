package com.ebanking.utils.processor;

import java.util.function.BiFunction;

public interface Joiner<T1, T2, O> extends BiFunction<T1, T2, O> {
    O join(T1 t1, T2 t2);

    @Override
    default O apply(T1 t1, T2 t2) {
        return join(t1, t2);
    }


}
