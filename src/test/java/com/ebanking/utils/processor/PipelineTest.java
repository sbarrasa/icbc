package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PipelineTest {
    public abstract static class Autor {
        public static Autor of(String firstName, String lastname) {
            return new Autor() {
                @Override
                public String getFirstName() {
                    return firstName;
                }

                @Override
                public String getLastName() {
                    return lastname;
                }
            };
        }

        public abstract String getFirstName();
        public abstract String getLastName();
    }
    static Map<LocalDate, Autor> nacimientos = Map.of(
            LocalDate.of(1974, 6, 7), Autor.of("Sebastián 'Zaiper'", "Barrasa"),
            LocalDate.of(1914, 8, 26), Autor.of("Julio Florencio", "Cortazar"),
            LocalDate.of(1891, 8, 17), Autor.of("Oliverio", "Girondo")
    );


    @Test
    void pipeline() {

        Converter<String, LocalDate> toDate = input ->
                LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Service<LocalDate, Autor> getbyBirthDay = nacimientos::get;

        Validator<Autor> exists = Validator.build(Objects::nonNull);

        Transformer<Autor, String> fullName = autor -> "%s, %s".formatted(
                autor.getLastName(),
                autor.getFirstName());

        var pipeline = Pipeline.<String>start()
                .andThen(toDate)
                .andThen(getbyBirthDay)
                .andThen(exists)
                .andThen(fullName)
                .andThen(String::trim);


        assertEquals("Barrasa, Sebastián 'Zaiper'", pipeline.apply("07/06/1974"));
        assertDoesNotThrow(() -> pipeline.apply("07/06/1974"));
        assertThrows(Exception.class, () -> pipeline.apply("01/01/1914"));
    }



}