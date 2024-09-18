package com.ebanking.utils.dsf;

import com.ebanking.model.Response;
import com.ebanking.model.ResponseHeader;
import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.Validable;
import com.ebanking.utils.validator.Validator;

public class ResponseValidator<D> implements Validable<GenericResponse<D>> {
  public static String NO_DATA_MESSAGE = "La respuesta no obtuvo datos";

  public static final Validator<Object> notNullDataValidator = new NotNullValidator()
      .setExceptionMessage(NO_DATA_MESSAGE);
  public static final ResponseHeaderValidator headerValidator = new ResponseHeaderValidator();

  @Override
  public final void validate(GenericResponse<D> response) throws Exception {
    validateHeader(response.getHeader());
    validateData(response.getData());
  }

  public final void validate(Response response) throws Exception {
    validateHeader(response.getHeader());
  }

  protected void validateHeader(ResponseHeader responseHeader) throws Exception {
    headerValidator.validate(responseHeader);
  }

  protected void validateData(D data) throws Exception {
    notNullDataValidator.validate(data);
  }


}
