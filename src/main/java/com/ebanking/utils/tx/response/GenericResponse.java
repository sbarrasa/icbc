package com.ebanking.utils.tx.response;

import com.ebanking.model.ResponseHeader;

public interface GenericResponse<D> {
  ResponseHeader getHeader();
  D getData();
}
