package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static com.ebanking.utils.fiscal.EntityType.COMPANY;
import static com.ebanking.utils.fiscal.EntityType.UNIPERSONAL;
import static org.junit.jupiter.api.Assertions.*;

class EntityTypeTest {
    // Test de conversión básica
    @Test
    void codeToEntityType() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("20"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("30"));
    }

    // Test para código no válido
    @Test
    void codeNotMapped()  {
        assertThrows(Exception.class, () ->  EntityType.codeConverter.convert("10"));
    }

    // Test para verificar otros códigos cercanos en el mapa
    @Test
    void codeToEntityTypeBoundaries() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("27"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("50"));
    }

    // Test para verificar código fuera del rango (fuera del mapa)
    @Test
    void codeToEntityTypeOutOfRange()  {
        assertThrows(Exception.class, () ->  EntityType.codeConverter.convert("1101"));
    }

    // Test para verificar que el validador detecta códigos no numéricos
    @Test
    void invalidCode() {
        var code = "abc";
        Exception exception = assertThrows(Exception.class, () -> EntityType.codeConverter.convert(code));
        assertEquals(EntityType.codeValidator.getExceptionMessage().formatted(code), exception.getMessage());
    }

    // Test para códigos más largos, verifica que solo los primeros dos dígitos sean considerados
    @Test
    void codeWithExtraDigits() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("201234"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("301234"));
    }

    // Test para códigos muy cortos, que deberían ser inválidos
    @Test
    void codeTooShort() {
        var code = "2";
        Exception exception = assertThrows(Exception.class, () -> EntityType.codeConverter.convert(code));
        assertEquals(EntityType.codeValidator.getExceptionMessage().formatted(code), exception.getMessage());
    }

    // Test para verificar si pasa el código de validación correcto
    @Test
    void validCodeWithLeadingZeros() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.codeConverter.convert("200"));
        assertEquals(COMPANY, EntityType.codeConverter.convert("300"));
    }

}