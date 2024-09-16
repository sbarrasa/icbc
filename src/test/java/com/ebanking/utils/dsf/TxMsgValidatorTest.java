package com.ebanking.utils.dsf;

import com.ebanking.dsf.Header;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TxMsgValidatorTest {

  @Test
  public void testRequestValidatorHeaderOkDataNotAssigned()  {
    var validator = new TxMsgValidator();
    Header header = new Header();
    header.setResultCode("ok");
    TxMsgAdapter txMsgAdapter = new TxMsgAdapter(header);

    assertDoesNotThrow(() -> validator.validate(txMsgAdapter));
  }

  @Test
  public void testRequestValidatorHeaderOkDataNull() {
    var validator = new TxMsgValidator();
    Header header = new Header();
    header.setResultCode("ok");
    TxMsgAdapter txMsgAdapter = new TxMsgAdapter(header, null);

    Exception exception = assertThrows(Exception.class, () -> validator.validate(txMsgAdapter));
    assertEquals(TxMsgValidator.NO_DATA_MESSAGE, exception.getMessage());

  }

  @Test
  public void testRequestValidatorHeaderOkDataNotNull()  {
    var validator = new TxMsgValidator();
    Header header = new Header();
    header.setResultCode("ok");
    TxMsgAdapter txMsgAdapter = new TxMsgAdapter(header, "Hola mundo");


    assertDoesNotThrow(() -> validator.validate(txMsgAdapter));
  }

  @Test
  public void testRequestValidatorHeaderErr()  {
    var validator = new TxMsgValidator();
    Header header = new Header();
    header.setResultCode("err");
    header.setMessageCode("BL123456");

    header.setMessageDescription("Se rompió todo");
    TxMsgAdapter txMsgAdapter = new TxMsgAdapter(header, "Hola mundo");

    Exception exception = assertThrows(Exception.class, () -> validator.validate(txMsgAdapter));
    assertEquals(HeaderValidator.HEADER_ERROR_MESSAGE
            .formatted(
                    txMsgAdapter.getHeader().getMessageCode(),
                    txMsgAdapter.getHeader().getMessageDescription()),
            exception.getMessage());
  }


}
