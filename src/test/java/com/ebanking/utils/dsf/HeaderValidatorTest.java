package com.ebanking.utils.dsf;

import com.ebanking.dsf.Header;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderValidatorTest {
  @Test
  public void testHeaderValidatorWithValidResultCode() {
    Header header = new Header();
    header.setMessageCode("MSG001");
    header.setMessageDescription("Message Description");
    header.setResultCode("ok");
    header.setTransactionId("TX12345");

    HeaderValidator validator = new HeaderValidator();
    assertDoesNotThrow(() -> validator.validate(header));
  }

  @Test
  public void testHeaderValidatorWithInvalidResultCode() {
    Header header = new Header();
    header.setMessageCode("MSG001");
    header.setMessageDescription("Message Description");
    header.setResultCode("err");
    header.setTransactionId("TX12345");

    HeaderValidator validator = new HeaderValidator();
    assertThrows(Exception.class, ()-> validator.validate(header));
  }
}