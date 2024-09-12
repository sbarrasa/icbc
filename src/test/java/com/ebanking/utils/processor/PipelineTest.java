package com.ebanking.utils.processor;

import com.ebanking.utils.types.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PipelineTest {
    public static final List<Pair<String, LocalDate>> authorsMap = List.of(
            Pair.of("Sebastián Zaiper Barrasa", LocalDate.of(1974, 6, 7)),
            Pair.of("Julio Florencio Cortazar", LocalDate.of(1914, 8, 26)),
            Pair.of("Oliverio Girondo",LocalDate.of(1891, 8, 17)),
            Pair.of("Sebastián Perez", LocalDate.of(1914, 1, 1))
    );

    @Test
    void test() throws Exception {
        Service<Integer, Pair<String, LocalDate>> byIndexService = authorsMap::get;

        BiFunction<String, LocalDate, String> authorToStringConverter = (name, date) ->
                String.format("%s (%s)",
                        name, date);

        Function<Integer, List<Pair<String, LocalDate>>> findByYearService = year ->
                authorsMap.stream()
                        .filter(author -> author.getData2().getYear() == year)
                        .collect(Collectors.toList());

        Function<String, Pair<String, LocalDate>> findByName =  name ->
                authorsMap.stream()
                .filter(author -> author.getData1().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Author not found with Name")
                );

        Joiner<Integer, List<Pair<String ,LocalDate>>, Pair<Integer, List<Pair<String, LocalDate>>>>
                yearAuthorJoiner = Pair::of;

        int year = 1914;

        // Pipeline
        Pair<String, LocalDate> autor = byIndexService.get(2);

        List<Pair<String, LocalDate>> authorsList = findByYearService.apply(year);
        String authorString = authorToStringConverter.apply(autor.getData1(), autor.getData2());

        var join = yearAuthorJoiner.join(
                1914,
                findByYearService.apply(1914));

        assertEquals(1914, join.getData2().get(0).getData2().getYear());

    }
}