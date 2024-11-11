package com.ebanking.utils.validator;

import com.ebanking.utils.range.Range;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrSizeDigitValidatorTest {
    @Test
    void validateTest(){
        var validator = new StrSizeDigitValidator<>(new Range<>(3,9));

        assertThrows(RuntimeException.class, () ->validator.validate(null));
        assertThrows(RuntimeException.class, () ->validator.validate("  "));
        assertThrows(RuntimeException.class, () ->validator.validate("12"));
        assertThrows(RuntimeException.class, () ->validator.validate("ABCD"));
        assertThrows(RuntimeException.class, () ->validator.validate("0123456789"));
        assertDoesNotThrow(() ->validator.validate("123"));

    }

}
