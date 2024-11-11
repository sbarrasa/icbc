package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    @Test
    void convertTest()  {
        var converter = new Converter<Integer, String>() {
            @Override
            public String convert(Integer input) {
                return input.toString();
            }
        };

        assertEquals("15", converter.convert(15));

    }

}