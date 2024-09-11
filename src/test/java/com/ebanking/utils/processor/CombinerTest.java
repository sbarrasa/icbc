package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinerTest {

    @Test
    void combineObjects() {
        var dupla = Dupla.of("Cantidad", 10);

        Combiner<String, Integer, String> combiner = Combiner.of("%s: %d"::formatted);

        String result = combiner.apply(dupla);

        assertEquals("Cantidad: 10", result, "El resultado de la combinación debería ser 'Cantidad: 10'.");
    }
}
