package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static com.ebanking.utils.fiscal.EntityType.COMPANY;
import static com.ebanking.utils.fiscal.EntityType.UNIPERSONAL;
import static org.junit.jupiter.api.Assertions.*;

class EntityTypeConverterTest {

    @Test
    void codeToEntityType() throws Exception {
        assertEquals(UNIPERSONAL, EntityType.converter.convert("20"));
        assertEquals(COMPANY, EntityType.converter.convert("30"));

        assertNull(EntityType.converter.convert("10"));
        assertEquals(UNIPERSONAL, EntityType.converter.convert("22"));
        assertEquals(COMPANY, EntityType.converter.convert("51"));

    }


}