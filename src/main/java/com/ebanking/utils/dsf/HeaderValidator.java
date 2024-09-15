package com.ebanking.utils.dsf;

import com.ebanking.dsf.Header;
import com.ebanking.utils.validator.Validator;

import java.util.function.Predicate;

public class HeaderValidator extends Validator<Header> {
  public static String HEADER_ERROR_MESSAGE = "Error: %s";

  public HeaderValidator(){
    setExceptionMessage(HEADER_ERROR_MESSAGE);
  }

  @Override
  protected Predicate<Header> getCondition() {
    return header -> header.getResultCode().equals("ok");
  }
}
