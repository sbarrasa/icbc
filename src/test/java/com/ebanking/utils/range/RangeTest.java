package com.ebanking.utils.range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

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