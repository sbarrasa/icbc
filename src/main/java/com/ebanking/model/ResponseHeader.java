package com.ebanking.model;

import java.io.Serializable;
import java.util.Objects;

public class ResponseHeader implements Serializable {
  private static final long serialVersionUID = 8360935829005526009L;
  protected String messageCode;
  protected String messageDescription;
  protected String resultCode;
  protected String transactionId;

  public ResponseHeader() {
  }

  public ResponseHeader messageCode(String messageCode) {
    this.messageCode = messageCode;
    return this;
  }

  public String getMessageCode() {
    return this.messageCode;
  }

  public void setMessageCode(String messageCode) {
    this.messageCode = messageCode;
  }

  public ResponseHeader messageDescription(String messageDescription) {
    this.messageDescription = messageDescription;
    return this;
  }

  public String getMessageDescription() {
    return this.messageDescription;
  }

  public void setMessageDescription(String messageDescription) {
    this.messageDescription = messageDescription;
  }

  public ResponseHeader resultCode(String resultCode) {
    this.resultCode = resultCode;
    return this;
  }

  public String getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }




  public ResponseHeader transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public String getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      ResponseHeader responseHeader = (ResponseHeader)o;
      return Objects.equals(this.messageCode, responseHeader.messageCode) && Objects.equals(this.messageDescription, responseHeader.messageDescription) && Objects.equals(this.resultCode, responseHeader.resultCode) ;
    } else {
      return false;
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[]{this.messageCode, this.messageDescription, this.resultCode});
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": ").append("\"ResponseHeader\"");
    if (this.messageCode != null) {
      sb.append(", ");
      sb.append("\"messageCode\": ");
      sb.append("\"");
      sb.append(this.messageCode);
      sb.append("\"");
    }

    if (this.messageDescription != null) {
      sb.append(", ");
      sb.append("\"messageDescription\": ");
      sb.append("\"");
      sb.append(this.messageDescription);
      sb.append("\"");
    }

    if (this.resultCode != null) {
      sb.append(", ");
      sb.append("\"resultCode\": ");
      sb.append("\"");
      sb.append(this.resultCode);
      sb.append("\"");
    }


    if (this.transactionId != null) {
      sb.append(", ");
      sb.append("\"transactionId\": ");
      sb.append("\"");
      sb.append(this.transactionId);
      sb.append("\"");
    }


    sb.append("}");
    return sb.toString();
  }
}