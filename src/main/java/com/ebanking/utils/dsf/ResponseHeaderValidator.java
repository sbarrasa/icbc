package com.ebanking.utils.dsf;

import com.ebanking.model.ResponseHeader;
import com.ebanking.utils.validator.Validator;

import java.util.function.Predicate;

public class ResponseHeaderValidator extends Validator<ResponseHeader> {
  public static String HEADER_ERROR_MESSAGE = "Error (%s) %s";

  public ResponseHeaderValidator(){
    this.setExceptionMessage(HEADER_ERROR_MESSAGE);
    this.setExceptionHandler((message, header) -> new RuntimeException(
            message.formatted(
                    header.getMessageCode(),
                    header.getMessageDescription())
            )
    );
  }

  @Override
  protected Predicate<ResponseHeader> getCondition() {
    return header -> header.getResultCode().equals("ok");
  }
}
