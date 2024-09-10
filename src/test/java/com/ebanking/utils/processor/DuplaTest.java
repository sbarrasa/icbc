package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DuplaTest {

    @Test
    void dupla() {
        var apellido = "Barrasa";
        var nombre = "Sebastián Gabriel";
        var nombres = Dupla.of(apellido, nombre);

        assertEquals(apellido, nombres.getLeft());
        assertEquals(nombre, nombres.getRight());

    }

    @Test
    void property(){
        Dupla<String, Integer> property = Dupla.of("Cantidad", 10);

        assertEquals(10, property.getRight());
    }
}