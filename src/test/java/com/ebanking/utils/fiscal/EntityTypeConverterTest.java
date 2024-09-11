package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static com.ebanking.utils.fiscal.EntityType.COMPANY;
import static com.ebanking.utils.fiscal.EntityType.UNIPERSONAL;
import static org.junit.jupiter.api.Assertions.*;

class EntityTypeConverterTest {

    @Test
    void codeToEntityType() {
        var converter = EntityTypeConverter.instance;

        assertEquals(UNIPERSONAL, converter.convert(20));
        assertEquals(COMPANY, converter.convert(30));

        assertNull(converter.convert(10));
        assertEquals(UNIPERSONAL, converter.convert(22));
        assertEquals(COMPANY, converter.convert(51));

    }

    @Test
    void entityTypeToCodes() {
        var converter = EntityTypeConverter.instance;
        assertFalse(converter.getCodes(UNIPERSONAL).isEmpty());
        assertFalse(converter.getCodes(COMPANY).isEmpty());
        assertTrue(converter.getCodes(UNIPERSONAL).size() >= 1);
        assertTrue(converter.getCodes(COMPANY).size() >= 1);
    }

    @Test
    void entityTypeToCode(){
        var converter = EntityTypeConverter.instance;
        assertEquals(20, converter.convert(UNIPERSONAL));
        assertEquals(30, converter.convert(COMPANY));

    }


}