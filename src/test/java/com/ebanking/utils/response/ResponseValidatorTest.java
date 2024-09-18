package com.ebanking.utils.response;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;
import com.ebanking.utils.tx.response.GenericResponseAdapter;
import com.ebanking.utils.tx.response.ResponseHeaderValidator;
import com.ebanking.utils.tx.response.ResponseValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseValidatorTest {

  @Test
  public void testValidateResponseOk()  {
    var validator = new ResponseValidator<>();

    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("ok");

    assertDoesNotThrow(() -> validator.validate(response));
  }

  @Test
  public void testValidateResponseErr()  {
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


  @Test
  public void testValidateGenericResponseWithNUllData() {
    var validator = new ResponseValidator<String>();
    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("ok");

    var responseAdapter = new GenericResponseAdapter<String>(response, null);

    Exception exception = assertThrows(Exception.class, () -> validator.validate(responseAdapter));
    assertEquals(ResponseValidator.NO_DATA_MESSAGE, exception.getMessage());

  }

  @Test
  public void testValidateGenericResponseWithNotNullData()  {
    var validator = new ResponseValidator<String>();
    var response = new Response();
    response.setHeader(new ResponseHeader());
    response.getHeader().setResultCode("ok");

    var responseAdapter = new GenericResponseAdapter<>(response, "Hola mundo");

    assertDoesNotThrow(() -> validator.validate(responseAdapter));
  }



}
