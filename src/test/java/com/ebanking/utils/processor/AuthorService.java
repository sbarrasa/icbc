package com.ebanking.utils.processor;

import java.time.LocalDate;
import java.util.Map;

public class AuthorService  {
  private static final Map<LocalDate, Author> authorsMap = Map.of(
          LocalDate.of(1974, 6, 7), new Author("Sebastián Zaiper", "Barrasa"),
          LocalDate.of(1914, 8, 26), new Author(("Julio Florencio"), "Cortazar"),
          LocalDate.of(1891, 8, 17), new Author("Oliverio", "Girondo")
          );

  public static Author get(LocalDate input) {
    return authorsMap.get(input);
  }


}
