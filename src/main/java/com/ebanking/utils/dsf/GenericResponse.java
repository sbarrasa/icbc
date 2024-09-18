package com.ebanking.utils.dsf;

import com.ebanking.model.ResponseHeader;

public interface GenericResponse<D> {
  ResponseHeader getHeader();
  D getData();
}
