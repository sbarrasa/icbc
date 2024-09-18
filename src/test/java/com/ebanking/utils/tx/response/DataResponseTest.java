package com.ebanking.utils.tx.response;

import com.ebanking.model.ResponseHeader;
import com.ebanking.utils.fiscal.Cuit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataResponseTest {
  static class CuitResponse extends DataResponse<Cuit> {}

  @Test
  void testCreate() throws Exception {

    var cuitResponseAdapter = GenericResponseAdapterTest.createCuitResponse();
    var cuit = cuitResponseAdapter.getData();

    var cuitResponse = new CuitResponse();
    cuitResponse.setHeader(new ResponseHeader());
    cuitResponse.getHeader().setResultCode("Ok");
    cuitResponse.setData(cuit);

    assertEquals(cuitResponse.getData(), cuitResponseAdapter.getData());

  }

}
