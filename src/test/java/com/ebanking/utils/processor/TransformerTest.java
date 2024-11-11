package com.ebanking.utils.processor;

import com.ebanking.utils.fiscal.Cuit;
import com.ebanking.utils.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransformerTest {
    @Test

    void transformTest() throws Exception {
        var transformer = new Transformer<Pair<String, Cuit>, String>() {
            @Override
            public String transform(Pair<String, Cuit> input)  {

                return input.data1()+": "+input.data2();
            }
        };
        var name = "Juan Carlos";
        var cuit = Cuit.of("20-12345678-9");
        var person = new Pair<>(name, cuit);

        assertEquals(name+": "+cuit, transformer.transform(person));

    }

}