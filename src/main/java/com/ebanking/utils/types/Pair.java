package com.ebanking.utils.types;


import java.util.Objects;

public interface Pair<T1, T2> {
    T1 getData1();
    T2 getData2();

    static <T1, T2> Pair<T1, T2> of(T1 data1, T2 data2) {
        return new Pair<>() {
            @Override
            public T1 getData1() {
                return data1;
            }

            @Override
            public T2 getData2() {
                return data2;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair<?, ?> pair = (Pair<?, ?>) o;
                return Objects.equals(data1, pair.getData1()) && Objects.equals(data2, pair.getData2());
            }

            @Override
            public int hashCode() {
                return Objects.hash(data1, data2);
            }

            @Override
            public String toString() {
                return "(%s, %s)".formatted(
                        getData1().toString(), getData2().toString()
                );
            }
        };
    }
}