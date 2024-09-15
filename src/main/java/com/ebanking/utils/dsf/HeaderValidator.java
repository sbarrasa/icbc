package com.ebanking.utils.dsf;

import com.ebanking.dsf.Header;
import com.ebanking.utils.validator.Validator;

import java.util.function.Predicate;

public class HeaderValidator extends Validator<Header> {
  public static String HEADER_ERROR_MESSAGE = "Error (%s) %s";

  public HeaderValidator(){
    getExceptionBuilder().setMessage(HEADER_ERROR_MESSAGE);

    getExceptionBuilder().setMessageBuilder((message, header) -> message.formatted(
            header.getMessageCode(),
            header.getMessageDescription()));
  }

  @Override
  protected Predicate<Header> getCondition() {
    return header -> header.getResultCode().equals("ok");
  }
}
