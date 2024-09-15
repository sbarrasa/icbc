package com.ebanking.utils.validator;

import com.ebanking.utils.types.MessageBuilder;

public class ExceptionBuilder<V> {

  private String message = "Exception: %s";
  private ExceptionHandler exceptionHandler = RuntimeException::new;
  private MessageBuilder<V> messageBuilder = String::formatted;

  private V value;
  public Exception build(V value) {
    this.value = value;
    return exceptionHandler.apply(getMessage());
  }

  public ExceptionBuilder<V> setMessage(String message) {
    this.message = message;
    return this;
  }

  public ExceptionBuilder<V> setExceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
    return this;
  }

  public ExceptionBuilder<V> setMessageBuilder(MessageBuilder<V> messageBuilder) {
    this.messageBuilder = messageBuilder;
    return this;
  }
  public String getMessage() {
    return messageBuilder.build(message, value);
  }


}
