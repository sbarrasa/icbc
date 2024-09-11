package com.ebanking.utils.processor;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface Combiner<T1, T2, O> extends Function<Pair<T1, T2>, O> {

    @Override
    O apply(Pair<T1, T2> input);

    static <T1, T2, O> Combiner<T1, T2, O> of(BiFunction<T1, T2, O> combinerFunction) {
        return input -> combinerFunction.apply(input.getFirst(), input.getSecond());
    }



}