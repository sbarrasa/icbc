package com.ebanking.utils.types;

import com.ebanking.utils.types.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

    @Test
    void test() {
        var apellido = "Barrasa";
        var nombre = "Sebastián Gabriel";
        var nombres = Pair.of(apellido, nombre);

        assertEquals(apellido, nombres.getData1());
        assertEquals(nombre, nombres.getData2());

    }

    @Test
    void property(){
        Pair<String, Integer> property = Pair.of("Cantidad", 10);

        assertEquals(10, property.getData2());
    }

}