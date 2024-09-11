package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

    @Test
    void dupla() {
        var apellido = "Barrasa";
        var nombre = "Sebastián Gabriel";
        var nombres = Pair.of(apellido, nombre);

        assertEquals(apellido, nombres.getFirst());
        assertEquals(nombre, nombres.getSecond());

    }

    @Test
    void property(){
        Pair<String, Integer> property = Pair.of("Cantidad", 10);

        assertEquals(10, property.getSecond());
    }
}