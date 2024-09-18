package com.ebanking.utils.dsf;

import com.ebanking.model.Response;
import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.Validable;
import com.ebanking.utils.validator.Validator;

public class ResponseValidator<T> implements Validable<Response> {
  public static String NO_DATA_MESSAGE = "La respuesta no obtuvo datos";

  public static final Validator<Object> notNullDataValidator = new NotNullValidator()
      .setExceptionMessage(NO_DATA_MESSAGE);
  public static final ResponseHeaderValidator headerValidator = new ResponseHeaderValidator();


  @Override
  public void validate(Response response) throws Exception {
    headerValidator.validate(response.getHeader());
  }

  public void validate(GenericResponseAdapter<T> response) throws Exception {
    headerValidator.validate(response.getHeader());
    notNullDataValidator.validate(response.getData());
  }

}
