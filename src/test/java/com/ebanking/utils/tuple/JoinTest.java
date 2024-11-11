package com.ebanking.utils.tuple;

import com.ebanking.utils.fiscal.Cuit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JoinTest {

  @Test
  void test2() {
    var join1 = Join.of("Item", 13);
    var join2 = Join.of("Item", 25);

    assertNotEquals(join1.data2(), join2.data2());
    assertEquals(join1.data1(), join2.data1());
  }

  @Test
  void test3() throws Exception {
    Cuit cuit = Cuit.of("20-12345678-0");
    String name = "Sebastián Zaiper";
    String lastName = "Barras";

    var join = Join.of(name, lastName, cuit);

    assertEquals(name, join.data1());
    assertEquals(lastName, join.data2());
    assertEquals(cuit, join.data3());
  }

}
