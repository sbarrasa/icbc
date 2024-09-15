package com.ebanking.utils.dsf;

import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.Validable;
import com.ebanking.utils.validator.Validator;

public class TxMsgValidator implements Validable<TxMsgAdapter> {
  public static String NO_DATA_MESSAGE = "La respuesta no obtuvo datos";

  public static final Validator<Object> notNullDataValidator = new NotNullValidator()
      .setExceptionMessage(NO_DATA_MESSAGE);
  public static final HeaderValidator headerValidator = new HeaderValidator();


  @Override
  public void validate(TxMsgAdapter txMsgAdapter) throws Exception {
    headerValidator.validate(txMsgAdapter.getHeader());
    if(txMsgAdapter.isDataIsAssigned())
      notNullDataValidator.validate(txMsgAdapter.getData());
  }


}
