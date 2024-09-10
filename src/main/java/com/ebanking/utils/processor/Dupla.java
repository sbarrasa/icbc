package com.ebanking.utils.processor;

public interface Dupla<L, R> {
    L getLeft();
    R getRight();

    static <L, R> Dupla<L, R> of(L left, R right) {
        return new DefaultDupla<>(left, right);
    }

    class DefaultDupla<L, R> implements Dupla<L, R> {
        private final L left;
        private final R right;

        DefaultDupla(L left, R right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public L getLeft() {
            return left;
        }

        @Override
        public R getRight() {
            return right;
        }
    }
}