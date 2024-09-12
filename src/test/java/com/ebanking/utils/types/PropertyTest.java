package com.ebanking.utils.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void propertyWithValue() {
        var property = new Property<>("Indice", 1);
        assertEquals("Indice", property.getName());
        assertEquals(1, property.getValue());
    }

    @Test
    void propertyWithoutValue() {
        var property = new Property<Integer>("Indice");
        assertNull(property.getValue());
    }

    @Test
        void changeValue() {
        var property = new Property<>("Indice", 1);

        assertEquals(1, property.getValue(), "El valor inicial debería ser 1.");

        property.setValue(42);

        assertEquals(42, property.getValue(), "El valor debería haber cambiado a 42.");
    }

}