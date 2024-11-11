package com.ebanking.utils.tuple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {
    @Test
    void test() {
        var apellido = "Barrasa";
        var nombre = "Sebastián Gabriel";
        var nombres = new Pair<>(apellido, nombre);

        assertEquals(apellido, nombres.data1());
        assertEquals(nombre, nombres.data2());
    }

    @Test
    void propertyTest(){
        var property = new Pair<>("Cantidad", 10);
        assertEquals(10, property.data2());
    }

}