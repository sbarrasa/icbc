package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;


class QueryBuilderTest {
  static Map<LocalDate, String> nacimientos = Map.of(
          LocalDate.of(1974, 6, 7), "Sebastián 'Zaiper' Barrasa",
          LocalDate.of(1914, 8, 26), "Julio Florencio Cortazar",
          LocalDate.of(1891, 8, 17), "Oliverio Girondo");

  @Test
  void executeWithValidable() throws Exception {

    var validator =  new AbstractValidator<String>() {
      @Override
      public Predicate<String> getCondition() {
        return Objects::nonNull;
      }
    };

    var Query = new QueryBuilder<String, LocalDate, String>(
          (inputData) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(inputData, formatter);
            },
          nacimientos::get,
          validator);

    assertEquals("Julio Florencio Cortazar", Query.execute("26/08/1914"));
    assertThrows(ValidatorException.class, ()-> Query.execute("01/01/2024"));

  }

  @Test
  void executeWithValidator() throws Exception {
   var Query = new QueryBuilder<String, LocalDate, String>(
           (inputData) -> {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             return LocalDate.parse(inputData, formatter);
           },
           (request) -> QueryBuilderTest.nacimientos.get(request),
           new Validator<>(Objects::nonNull));

    assertEquals("Julio Florencio Cortazar", Query.execute("26/08/1914"));
    assertThrows(ValidatorException.class, ()-> Query.execute("01/01/2024"));

  }

  @Test
  void executeWithCondition() throws Exception {
    var Query = new QueryBuilder<String, LocalDate, String>(
            (inputData) -> {
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
              return LocalDate.parse(inputData, formatter);
            },
            (request) -> QueryBuilderTest.nacimientos.get(request),
            Objects::nonNull);

    assertEquals("Julio Florencio Cortazar", Query.execute("26/08/1914"));
    assertThrows(ValidatorException.class, ()-> Query.execute("01/01/2024"));
  }

  @Test
  void executeWithException()  {

    var Query = new QueryBuilder<String, LocalDate, String>(
        (inputData) -> {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          return LocalDate.parse(inputData, formatter);
        },
        (request) -> QueryBuilderTest.nacimientos.get(request),
        (response) -> Objects.nonNull(response) && response.contains("Zaiper"),
        RuntimeException::new);

    assertDoesNotThrow(()-> Query.execute("07/06/1974"));
    var ex = assertThrows(RuntimeException.class, ()-> Query.execute("01/01/2024"));
    ex = assertThrows(RuntimeException.class, ()-> Query.execute("17/08/1891"));
    assertEquals(Query.validator().messageBuilder().get(), ex.getMessage());
  }

}