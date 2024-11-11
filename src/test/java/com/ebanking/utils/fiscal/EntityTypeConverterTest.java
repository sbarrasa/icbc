package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static com.ebanking.utils.fiscal.EntityType.COMPANY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityTypeConverterTest {
    @Test
    void test() throws Exception {
        var converter = new EntityTypeConverter();
        assertEquals(COMPANY, converter.convert("30"));
    }
}
