package com.ebanking.utils.processor;


public interface Dupla<L, R> {
    L getLeft();
    R getRight();

    static <L, R> Dupla<L, R> of(L left, R right) {
        return new Dupla<>() {
            @Override
            public L getLeft() {
                return left;
            }

            @Override
            public R getRight() {
                return right;
            }
        };
    }
}