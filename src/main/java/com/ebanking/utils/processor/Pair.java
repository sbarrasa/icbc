package com.ebanking.utils.processor;


import java.util.Objects;

public interface Pair<T1, T2> {
    T1 getFirst();
    T2 getSecond();

    static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
        return new Pair<>() {
            @Override
            public T1 getFirst() {
                return first;
            }

            @Override
            public T2 getSecond() {
                return second;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair<?, ?> pair = (Pair<?, ?>) o;
                return Objects.equals(first, pair.getFirst()) && Objects.equals(second, pair.getSecond());
            }

            @Override
            public int hashCode() {
                return Objects.hash(first, second);
            }
        };
    }


}