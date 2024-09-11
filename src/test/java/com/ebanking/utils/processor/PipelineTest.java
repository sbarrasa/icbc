package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PipelineTest {
    @Test
    void pipeline() {

      Converter<String, LocalDate> stringToDate = input ->
              LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));


      Service<LocalDate, Author> getAuthor = Service.of(AuthorService::get);

      Validator<Author> exists = Validator.<Author>build(Objects::nonNull)
              .exceptionHandler(RuntimeException::new);

      var process = stringToDate
                          .andThen(birthDay -> Pair.of(
                                                        birthDay,
                                                        getAuthor
                                                          .andThen(exists)
                                                             .apply(birthDay)
                                                        )
                          );

      var author1 = process.apply("07/06/1974");

      assertEquals("Barrasa, Sebastián Zaiper", author1.getSecond().getFullname());
      assertEquals(LocalDate.of(1974, 6,7), author1.getFirst());

      assertDoesNotThrow(() -> process.apply("07/06/1974"));
      assertThrows(Exception.class, () -> process.apply("01/01/1914"));
    }
}