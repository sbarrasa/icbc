package com.ebanking.utils.processor;

import com.ebanking.utils.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaveServiceTest {
    @Test
    void saveTest()  {
        var map = new HashMap<Integer, String>();

        var service = new SaveService<Pair<Integer, String>, String>() {
            @Override
            public String save(Pair<Integer, String> par)  {
                return map.put(par.data1(), par.data2());
            }


        };
        service.save(new Pair<>(1,"María"));

        assertEquals(1, map.size());

    }

}
