package com.ebanking.utils.processor;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface Combiner<L, R, O> extends Function<Dupla<L, R>, O> {

    @Override
    O apply(Dupla<L, R> input);

    static <L, R, O> Combiner<L, R, O> of(BiFunction<L, R, O> combinerFunction) {
        return input -> combinerFunction.apply(input.getLeft(), input.getRight());
    }



}