package com.ebanking.utils.tx.response;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;
import com.ebanking.utils.fiscal.Cuit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericResponseAdapterTest {
  static class CuitResponse extends Response {
    private final Cuit data;

    public Cuit getData() {
      return data;
    }

    public CuitResponse(Cuit cuit)  {
      this.setHeader(new ResponseHeader());
      this.data = cuit;
    }

  }


  static CuitResponse createCuitResponse() throws Exception {
    return new CuitResponse(Cuit.of("20-12345678-1"));
  }

  @Test
  void autoAdaptTest() throws Exception {
    var response = createCuitResponse();

    var responseAdapter = new GenericResponseAdapter<Cuit>(response);
    assertEquals(response.getData(), responseAdapter.getData());
    assertEquals(response.getHeader(), responseAdapter.getHeader());

  }

  @Test
  void manualAdaptTest() throws Exception {
    var response = createCuitResponse();

    var responseAdapter = new GenericResponseAdapter<>(response, response.getData());
    assertEquals(response.getData(), responseAdapter.getData());
    assertEquals(response.getHeader(), responseAdapter.getHeader());

  }
}
