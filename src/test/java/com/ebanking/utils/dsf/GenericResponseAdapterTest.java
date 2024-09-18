package com.ebanking.utils.dsf;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericResponseAdapterTest {
  static class PersonResponse extends Response {
    public PersonResponse(){
      this.setHeader(new ResponseHeader());
      this.data = new Person();
    }
    private final Person data;

    public Person getData() {
      return data;
    }
  }


  static PersonResponse createPersonRsponse(){
    var personResponse = new PersonResponse();
    personResponse.getHeader().setResultCode("Ok");
    personResponse.getData().setName("Sebastián Zaiper");
    personResponse.getData().setLastName("Barrasa");
    return personResponse;
  }

  @Test
  void autoAdaptTest() throws ReflectiveOperationException {
    var response = createPersonRsponse();

    var responseAdapter = new GenericResponseAdapter<Person>(response);
    assertEquals(response.getData(), responseAdapter.getData());
    assertEquals(response.getHeader(), responseAdapter.getHeader());

  }

  @Test
  void manualAdaptTest() {
    var response = createPersonRsponse();

    var responseAdapter = new GenericResponseAdapter<>(response, response.getData());
    assertEquals(response.getData(), responseAdapter.getData());
    assertEquals(response.getHeader(), responseAdapter.getHeader());

  }
}
