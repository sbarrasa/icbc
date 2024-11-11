package com.ebanking.utils.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrDigitValidatorTest {

  static StrDigitValidator<?> validator = new StrDigitValidator<>();

  @Test
  void validateOkTest(){
    assertDoesNotThrow(() -> validator.validate("12345"));
  }

  @Test
  void validateErrTest(){
    assertThrows(Exception.class, () -> validator.validate("ABCD1234"));
  }

  @Test
  void testIsDigits() {
    // Test casos válidos
    assertTrue(StrDigitValidator.isDigits.test("12345"));    // Solo dígitos
    assertTrue(StrDigitValidator.isDigits.test("0"));         // Solo un dígito

    // Test casos inválidos
    assertFalse(StrDigitValidator.isDigits.test(""));          // Cadena vacía
    assertFalse(StrDigitValidator.isDigits.test(null));        // Cadena nula
    assertFalse(StrDigitValidator.isDigits.test("123a45"));    // Contiene letras
    assertFalse(StrDigitValidator.isDigits.test("123 456"));   // Contiene espacio
    assertFalse(StrDigitValidator.isDigits.test("123.45"));    // Contiene punto
  }


}
