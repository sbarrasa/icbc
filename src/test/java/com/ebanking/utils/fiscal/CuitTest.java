package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuitTest {

    @Test
    void testConstructorFromStringWithSeparator() throws Exception {
        Cuit cuit = Cuit.of("20-12345678-9");
        assertEquals("20", cuit.getEntityTypeCode());
        assertEquals("12345678", cuit.getId());
        assertEquals("9", cuit.getVerificationDigit());
    }

    @Test
    void testConstructorFromStringWithoutSeparator() throws Exception {
        Cuit cuit = Cuit.of("20123456789");

        assertEquals("20", cuit.getEntityTypeCode());
        assertEquals("12345678", cuit.getId());
        assertEquals("9", cuit.getVerificationDigit());
    }

    @Test
    void testConstructorFromComponents() throws Exception {
        Cuit cuit = Cuit.of("20", "1234567", "9");
        assertEquals("20", cuit.getEntityTypeCode());
        assertEquals("1234567", cuit.getId());
        assertEquals("9", cuit.getVerificationDigit());
    }

    @Test
    void testGetEntityType() throws Exception {
        assertEquals(EntityType.UNIPERSONAL, Cuit.of("20-1234567-8").getEntityType());
        assertEquals(EntityType.COMPANY, Cuit.of("30-1234567-8").getEntityType());
    }

    @Test
    void testToString() throws Exception {
        Cuit cuit = Cuit.of("20123456789");
        assertEquals("20-12345678-9", cuit.toString());
    }

    @Test
    void testEquals() throws Exception {
        Cuit cuit1 = Cuit.of("20-1234567-9");
        Cuit cuit2 = Cuit.of("20-1234567-9");
        Cuit cuit3 = Cuit.of("21-1234567-9");

        assertEquals(cuit1, cuit2);
        assertNotEquals(cuit1, cuit3);
    }


    @Test
    void testHashCode() throws Exception {
        Cuit cuit1 = Cuit.of("20-1234567-9");
        Cuit cuit2 = Cuit.of("20-1234567-9");
        assertEquals(cuit1.hashCode(), cuit2.hashCode());
    }

    @Test
    void testParseWithSeparatorException() {
        Exception exception = assertThrows(Exception.class, () -> Cuit.of("20-1234567"));
        assertEquals(Cuit.partsCountValidator.getExceptionMessage(), exception.getMessage());
    }

    @Test
    void testParseWithoutSeparatorException() {
        Exception exception = assertThrows(Exception.class, () -> {
            Cuit.of("201234567"); // Invalid length
        });
        assertEquals(Cuit.sizeValidator.getExceptionMessage(), exception.getMessage());
    }
}
