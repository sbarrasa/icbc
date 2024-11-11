package com.ebanking.utils.range;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
  @Test
  void rangeEmptyTest() throws Exception {
    var range = new Range<Date>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    range.setMin(formatter.parse("07/06/1974"));
    range.setMax(new Date());

    assertTrue(range.contains(new Date()));
  }

  @Test
  void rangeCreateTest() {
    var range = new Range<>(1, 100);
    assertTrue(range.contains(15));
    assertFalse(range.contains(150));
  }

  @Test
  void toStringTest(){
    var range = new Range<>(1,2);

    assertEquals("Range[1,2]", range.toString());
  }
}