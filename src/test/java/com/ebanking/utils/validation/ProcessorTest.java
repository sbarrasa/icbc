package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {
    static Map<LocalDate, String> nacimientos = Map.of(
            LocalDate.of(1974, 6, 7), "Sebastián 'Zaiper' Barrasa",
            LocalDate.of(1914, 8, 26), "Julio Florencio Cortazar",
            LocalDate.of(1891, 8, 17), "Oliverio Girondo");


    @Test
    void pipeline() throws Exception {
        Function<String, String> pipeline = Function.<String>identity()
                .andThen(input -> LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .andThen(nacimientos::get)
                .andThen(new Validator<String>() {
                    @Override
                    protected Predicate<String> getCondition() {
                        return     value -> Objects.isNull(value)
                                || value.contains("Zaiper");
                    }
                }
                 .andThen(fullname -> fullname.substring(0, 10))
                .andThen(String::trim));


        assertEquals("Sebastián", pipeline.apply("07/06/1974"));
        assertDoesNotThrow(() -> pipeline.apply("07/06/1974"));
        var ex = assertThrows(Exception.class, () -> pipeline.apply("26/08/1914"));
        assertTrue(ex.getMessage().contains("Cortazar"));
    }



}