package com.ebanking.utils.dsf;

import com.ebanking.dsf.Request;
import com.ebanking.dsf.Response;
import com.ebanking.utils.processor.Service;

public class ServiceAdapter<I, O> implements Service<Request, Response>{
  @Override
  public Response get(Request input) throws Exception {
    return null;
  }


}
