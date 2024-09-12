package com.ebanking.utils.processor;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
  @Test
  void service() throws Exception {
    Service<LocalDate, Author> service = AuthorService::get;

    assertNull(service.get(LocalDate.now()));
    assertNotNull(service.get(LocalDate.of(1974,6,7)));
  }
}
