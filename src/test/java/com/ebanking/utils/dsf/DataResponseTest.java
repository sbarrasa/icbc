package com.ebanking.utils.dsf;

import com.ebanking.model.ResponseHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataResponseTest {
  static class PersonResponse extends DataResponse<Person> {}

  @Test
  void testCreate() {

    var personResponseAdapter = GenericResponseAdapterTest.createPersonRsponse();
    var person = personResponseAdapter.getData();

    var personResponse = new PersonResponse();
    personResponse.setHeader(new ResponseHeader());
    personResponse.getHeader().setResultCode("Ok");
    personResponse.setData(person);

    assertEquals(personResponse.getData(), personResponseAdapter.getData());

  }

}
