package com.ebanking.utils.dsf;

import com.ebanking.model.Response;

public class DataResponse<D> extends Response implements GenericResponse<D> {
  D data;

  public D getData(){
    return data;
  }

  public DataResponse<D> setData(D data){
    this.data = data;
    return this;
  }
}
