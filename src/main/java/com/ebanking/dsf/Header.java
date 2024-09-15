package com.ebanking.dsf;

public class Header {
  private String messageCode;
  private String messageDescription;
  private String resultCode;
  private String transactionId;


  public String getMessageCode() {
    return messageCode;
  }

  public void setMessageCode(String messageCode) {
    this.messageCode = messageCode;
  }

  public String getMessageDescription() {
    return messageDescription;
  }

  public void setMessageDescription(String messageDescription) {
    this.messageDescription = messageDescription;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
}
