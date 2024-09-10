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
}