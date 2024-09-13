package com.ebanking.utils.converter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringFormatConverter {
  @Test
  public void testToOk() throws Exception {
    var converter = new StringPatternConverter("%s-%s-%s");
    var result = converter.to(List.of("20", "24061470", "8"));
    assertEquals("20-24061470-8", result);
  }


}
