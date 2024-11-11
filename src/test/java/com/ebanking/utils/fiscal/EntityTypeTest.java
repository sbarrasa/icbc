package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static com.ebanking.utils.fiscal.EntityType.COMPANY;
import static com.ebanking.utils.fiscal.EntityType.UNIPERSONAL;
import static org.junit.jupiter.api.Assertions.*;

class EntityTypeTest {
    // Test de conversión básica
    @Test
    void codeToEntityTypeTest() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("20"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("30"));
    }

    // Test para código no válido
    @Test
    void codeNotMappedTest()  {
        assertThrows(Exception.class, () ->  EntityType.codeConverter.convert("10"));
    }

    // Test para verificar otros códigos cercanos en el mapa
    @Test
    void codeToEntityTypeBoundariesTest() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("27"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("50"));
    }

    @Test
    void codeToEntityTypeOutOfRangeTest()  {
        assertThrows(Exception.class, () ->  EntityType.codeConverter.convert("1101"));
    }

    @Test
    void invalidCodeTest() {
        var code = "abc";
        assertThrows(FiscalException.class, () -> EntityType.codeConverter.convert(code));
    }

    @Test
    void codeWithExtraDigitsTest() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("201234"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("301234"));
    }

    @Test
    void codigoMuyCortoTest() {
        var code = "2";
        assertThrows(FiscalException.class, () -> EntityType.codeConverter.convert(code));
     }

    // Test para verificar si pasa el código de validación correcto
    @Test
    void validCodeWithLeadingZerosTest() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("200"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("300"));
    }

}