package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QueryTemplateTest {
  static Map<LocalDate, String> nacimientos = Map.of(
          LocalDate.of(1891, 8, 17), "Oliverio Girondo",
          LocalDate.of(1914, 8, 26), "Julio Florencio Cortazar",
          LocalDate.of(1974, 6, 7), "Sebastián 'Zaiper' Barrasa");


  @Test
  void execute() throws Exception {

    var query = new QueryTemplate<String, LocalDate, String>() {
      private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

      @Override
      public LocalDate buildRequest(String inputData) {
        return LocalDate.parse(inputData, formatter);
      }

      @Override
      public String get(LocalDate request) {
        return nacimientos.get(request);
      }

      @Override
      public void validate() throws Exception {
        new Validator<>(Objects::nonNull)
                .setValue(this.getResponse())
                .exceptionHandler(validator -> new Exception("No hay autores nacidos en esa fecha"))
                .validate();

      }

    };

    assertThrows(Exception.class, ()-> query.execute("01/01/2024"));
    assertEquals("Julio Florencio Cortazar", query.execute("26/08/1914"));

  }


}