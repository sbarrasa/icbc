package com.ebanking.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
  @Serial
  private static final long serialVersionUID = -874716767157312622L;
  private RequestHeader header;

  public Request() {
  }

  public Request header(RequestHeader header) {
    this.header = header;
    return this;
  }

  public RequestHeader getHeader() {
    return this.header;
  }

  public void setHeader(RequestHeader header) {
    this.header = header;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      Request request = (Request)o;
      return Objects.equals(this.header, request.header);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[]{this.header});
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": ").append("\"Request\"");
    if (this.header != null) {
      sb.append(", ");
      sb.append("\"header\": ");
      sb.append(this.header);
    }

    sb.append("}");
    return sb.toString();
  }

  public RequestHeader createRequestHeader() {
    return this.header != null ? this.header : new RequestHeader();
  }

  public Response createResponse() {
    return new Response();
  }
}