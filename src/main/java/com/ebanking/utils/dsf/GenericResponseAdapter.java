package com.ebanking.utils.dsf;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;

public class GenericResponseAdapter<D> implements GenericData<D> {
  private final ResponseHeader header;
  private final D data;


  public GenericResponseAdapter(Response response, D data) {
    this.header = response.getHeader();
    this.data = data;
  }

  @Override
  public D getData() {
    return data;
  }
  public ResponseHeader getHeader(){
    return header;
  }

}
