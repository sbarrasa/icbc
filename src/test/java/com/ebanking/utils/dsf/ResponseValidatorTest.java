package com.ebanking.utils.dsf;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseValidatorTest {

  @Test
  public void testRequestValidatorHeaderOkDataNotAssigned()  {
    var validator = new ResponseValidator<>();

    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("ok");

    assertDoesNotThrow(() -> validator.validate(response));
  }

  @Test
  public void testRequestValidatorHeaderOkDataNull() {
    var validator = new ResponseValidator<String>();
    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("ok");

    var responseAdapter = new GenericResponseAdapter<String>(response, null);

    Exception exception = assertThrows(Exception.class, () -> validator.validate(responseAdapter));
    assertEquals(ResponseValidator.NO_DATA_MESSAGE, exception.getMessage());

  }

  @Test
  public void testRequestValidatorHeaderOkDataNotNull()  {
    var validator = new ResponseValidator<String>();
    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("ok");

    var responseAdapter = new GenericResponseAdapter<>(response, "Hola mundo");


    assertDoesNotThrow(() -> validator.validate(responseAdapter));
  }

  @Test
  public void testRequestValidatorHeaderErr()  {
    var validator = new ResponseValidator<>();
    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("err");
    response.getHeader().setMessageCode("BL123456");
    response.getHeader().setMessageDescription("Se rompió todo");

    Exception exception = assertThrows(Exception.class, () -> validator.validate(response));
    assertEquals(ResponseHeaderValidator.HEADER_ERROR_MESSAGE
            .formatted(response.getHeader().getMessageCode(),
                    response.getHeader().getMessageDescription()),
            exception.getMessage());
  }


}