package com.ebanking.utils.tx.response;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;

import java.lang.reflect.Field;

public class GenericResponseAdapter<D> implements GenericResponse<D> {
  private final Response response;
  private final D data;

  public GenericResponseAdapter(Response response) throws ReflectiveOperationException {
    this.response = response;
    this.data = retrieveData();
  }


  public GenericResponseAdapter(Response response, D data) {
    this.response = response;
    this.data = data;
  }

  @Override
  public D getData() {
    return data;
  }
  public ResponseHeader getHeader(){
    return response.getHeader();
  }

  @SuppressWarnings("unchecked")
  private D retrieveData() throws NoSuchFieldException, IllegalAccessException {
    Field dataField = response.getClass().getDeclaredField("data");
    dataField.setAccessible(true);
    return (D) dataField.get(response);
  }
}
