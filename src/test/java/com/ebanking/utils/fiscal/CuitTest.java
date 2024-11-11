package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuitTest {

    @Test
    void constructorOfStringWithSeparatorTest() throws Exception {
        Cuit cuit = Cuit.of("20-12345678-9");
        assertEquals("20", cuit.getentityCode());
        assertEquals("12345678", cuit.getId());
        assertEquals("9", cuit.getVerificationDigit());
    }

    @Test
    void constructorOfStringWithoutSeparatorTest() throws Exception {
        Cuit cuit = Cuit.of("20123456789");

        assertEquals("20", cuit.getentityCode());
        assertEquals("12345678", cuit.getId());
        assertEquals("9", cuit.getVerificationDigit());
    }

    @Test
    void constructorOfComponentsTest() throws Exception {
        Cuit cuit = Cuit.of("20", "1234567", "9");
        assertEquals("20", cuit.getentityCode());
        assertEquals("1234567", cuit.getId());
        assertEquals("9", cuit.getVerificationDigit());
    }

    @Test
    void getEntityTypeTest() throws Exception {
        assertEquals(EntityType.UNIPERSONAL, Cuit.of("20-1234567-8").getEntityType());
        assertEquals(EntityType.COMPANY, Cuit.of("30-1234567-8").getEntityType());
    }

    @Test
    void toStringTest() throws Exception {
        Cuit cuit = Cuit.of("20123456789");

        assertEquals("20123456789", cuit.toString());

        cuit.showSeparator(true);
        assertEquals("20-12345678-9", cuit.toString());
    }

    @Test
    void equalsTest() throws Exception {
        Cuit cuit1 = Cuit.of("20-1234567-9");
        Cuit cuit2 = Cuit.of("20-1234567-9");
        Cuit cuit3 = Cuit.of("21-1234567-9");

        assertEquals(cuit1, cuit2);
        assertNotEquals(cuit1, cuit3);
    }


    @Test
    void hashCodeTest() throws Exception {
        Cuit cuit1 = Cuit.of("20-1234567-9");
        Cuit cuit2 = Cuit.of("20-1234567-9");
        assertEquals(cuit1.hashCode(), cuit2.hashCode());
    }

    @Test
    void parseWithSeparatorExceptionTest() {
        Exception exception = assertThrows(Exception.class, () -> Cuit.of("20-1234567"));
        assertEquals(CuitConverter.partsCountValidator.getExceptionMessage(), exception.getMessage());
    }

    @Test
    void parseWithoutSeparatorExceptionTest() {
        Exception exception = assertThrows(Exception.class, () -> Cuit.of("201234567"));
        assertEquals(CuitConverter.sizeValidator.getExceptionMessage(), exception.getMessage());
    }


    @Test
    void printWithoutSeparatorTest() throws Exception {
        var cuit = Cuit.of("20-12345678-9");
        cuit.showSeparator(false);

        assertEquals("20123456789", cuit.toString());
    }

}
