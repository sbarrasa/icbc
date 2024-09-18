package com.ebanking.utils.dsf;

import com.ebanking.model.ResponseHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseHeaderValidatorTest {
  @Test
  public void testHeaderValidatorWithValidResultCode() {
    var header = new ResponseHeader();
    header.setMessageCode("MSG001");
    header.setMessageDescription("Message Description");
    header.setResultCode("ok");
    header.setTransactionId("TX12345");

    var validator = new ResponseHeaderValidator();
    assertDoesNotThrow(() -> validator.validate(header));
  }

  @Test
  public void testHeaderValidatorWithInvalidResultCode() {
    var header = new ResponseHeader();
    header.setMessageCode("MSG001");
    header.setMessageDescription("Message Description");
    header.setResultCode("err");
    header.setTransactionId("TX12345");

    var validator = new ResponseHeaderValidator();
    assertThrows(Exception.class, ()-> validator.validate(header));
  }
}