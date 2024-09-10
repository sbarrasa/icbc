package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {
    static Map<LocalDate, String> nacimientos = Map.of(
            LocalDate.of(1974, 6, 7), "Sebastián 'Zaiper' Barrasa",
            LocalDate.of(1914, 8, 26), "Julio Florencio Cortazar",
            LocalDate.of(1891, 8, 17), "Oliverio Girondo");

    @Test
    void executeWithPredicate() throws Exception {
        var processor = new Processor<String, LocalDate, String>(
                input -> LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                nacimientos::get,
                Objects::nonNull
        );

        assertTrue(processor.execute("07/06/1974").contains("Zaiper"));
        assertDoesNotThrow(() -> processor.execute("26/08/1914"));
        assertThrows(RuntimeException.class, () -> processor.execute("01/01/1920"));
    }

    @Test
    void executeWithValidator() throws Exception {
        var processor = new Processor<String, LocalDate, String>(
                input -> LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                nacimientos::get,
                new Validator<String>(value -> Objects.isNull(value)
                                             || value.contains("Zaiper"))
                        .exceptionHandler(value -> new Exception("%s no es Zaiper".formatted(value)))
        );

        assertDoesNotThrow(() -> processor.execute("07/06/1974"));
        var ex = assertThrows(Exception.class, () -> processor.execute("26/08/1914"));
        assertTrue(ex.getMessage().contains("Cortazar"));
    }

}