package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class QueryBuilderTest {
    static Map<LocalDate, String> nacimientos = Map.of(
            LocalDate.of(1974, 6, 7), "Sebastián 'Zaiper' Barrasa",
            LocalDate.of(1914, 8, 26), "Julio Florencio Cortazar",
            LocalDate.of(1891, 8, 17), "Oliverio Girondo");

    @Test
  void executeWithValidator() throws Exception {

    var query = new QueryBuilder<String, LocalDate, String>(
          (inputData) -> LocalDate.parse(inputData, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
          QueryBuilderTest.nacimientos::get,
          new Validator<>(Objects::nonNull));

    assertEquals("Julio Florencio Cortazar", query.execute("26/08/1914"));
    assertThrows(RuntimeException.class, ()-> query.execute("01/01/2024"));

  }


  @Test
  void executeWithCondition() throws Exception {
    var query = new QueryBuilder<String, LocalDate, String>(
            (inputData) -> LocalDate.parse(inputData, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            (request) -> QueryBuilderTest.nacimientos.get(request),
            Objects::nonNull);

    assertEquals("Julio Florencio Cortazar", query.execute("26/08/1914"));
    assertThrows(RuntimeException.class, ()-> query.execute("01/01/2024"));
  }

  @Test
  void executeWithException()  {

    var query = new QueryBuilder<String, LocalDate, String>(
        (inputData) -> LocalDate.parse(inputData, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        (request) -> QueryBuilderTest.nacimientos.get(request),
        (response) -> Objects.nonNull(response) && response.contains("Zaiper"),
        (validator) -> new RuntimeException("El autor %s no es Zaiper".formatted(validator.getData()))
    );

    assertDoesNotThrow(()-> query.execute("07/06/1974"));
    assertThrows(RuntimeException.class, ()-> query.execute("01/01/2024"));
    assertThrows(RuntimeException.class, ()-> query.execute("17/08/1891"));
  }

}