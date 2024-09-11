package com.ebanking.utils.processor;


import java.util.Objects;

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

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Dupla<?, ?> dupla = (Dupla<?, ?>) o;
                return Objects.equals(left, dupla.getLeft()) && Objects.equals(right, dupla.getRight());
            }

            @Override
            public int hashCode() {
                return Objects.hash(left, right);
            }
        };
    }
}