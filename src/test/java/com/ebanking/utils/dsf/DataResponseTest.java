package com.ebanking.utils.dsf;

import com.ebanking.model.ResponseHeader;
import com.ebanking.utils.fiscal.Cuit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataResponseTest {
  static class PersonResponse extends DataResponse<Cuit> {}

  @Test
  void testCreate() throws Exception {

    var personResponseAdapter = GenericResponseAdapterTest.createCuitResponse();
    var cuit = personResponseAdapter.getData();

    var personResponse = new PersonResponse();
    personResponse.setHeader(new ResponseHeader());
    personResponse.getHeader().setResultCode("Ok");
    personResponse.setData(cuit);

    assertEquals(personResponse.getData(), personResponseAdapter.getData());

  }

}
