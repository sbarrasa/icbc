package com.ebanking.utils.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QueryTemplateTest {

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
        return QueryBuilderTest.nacimientos.get(request);
      }

      @Override
      public void validate()  {
        if(getData() == null)
          throw new RuntimeException("No hay autores nacidos con esa fecha");
      }

    };

    assertEquals("Julio Florencio Cortazar", query.execute("26/08/1914"));
    assertThrows(RuntimeException.class, ()-> query.execute("01/01/2024"));

  }


}