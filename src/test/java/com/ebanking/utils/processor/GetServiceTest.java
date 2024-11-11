package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetServiceTest {
    @Test
    void getTest() throws Exception {
        var map = Map.of(1,"JUan",
                2, "Pedro",
                3, "María");

        var service = new GetService<Integer, String>() {
            @Override
            public String get(Integer input) throws Exception {
                var name = map.get(input);
                if(name == null) throw new Exception("No hay nadie");
                return name;
            }

        };

        assertEquals("María", service.get(3));
        assertThrows(Exception.class, ()-> service.get(10));


    }

}
