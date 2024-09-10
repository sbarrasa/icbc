package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinerTest {

    @Test
    void pipeline() {
        Combiner<Integer, Integer, Integer> combiner = Combiner.of((a, b) -> a * b);

        Dupla<Integer, Integer> dupla = Dupla.of(5, 10);

        var pipeline = Pipeline.<Dupla<Integer, Integer>>start()
                .andThen(combiner)
                .andThen(result -> result + 5);

        Integer result = pipeline.apply(dupla);

        assertEquals(55, result, "El resultado final del pipeline debería ser 55.");
    }

    @Test
    void combineObjects() {
        var property = Dupla.of("Cantidad", 10);

        BiFunction<String, Integer, String> concat = "%s: %d"::formatted;

        Combiner<String, Integer, String> combiner = Combiner.of(concat);

        String result = combiner.apply(property);

        assertEquals("Cantidad: 10", result, "El resultado de la combinación debería ser 'Cantidad: 10'.");
    }
}
