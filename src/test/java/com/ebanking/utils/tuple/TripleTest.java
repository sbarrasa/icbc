package com.ebanking.utils.tuple;

import com.ebanking.utils.fiscal.Cuit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripleTest {

    @Test
    void test() throws Exception {
        var apellido = "Barrasa";
        var nombre = "Sebastián Gabriel";
        var cuit = Cuit.of("30-12345678-9");
        var person = new Triple<>(apellido, nombre, cuit);

        assertEquals(apellido, person.data1());
        assertEquals(cuit, person.data3());

    }


}