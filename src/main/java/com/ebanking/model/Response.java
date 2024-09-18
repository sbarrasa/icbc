
package com.ebanking.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Response implements Serializable {
  @Serial
  private static final long serialVersionUID = -3432235852160467083L;
  private ResponseHeader header;

  public Response() {
  }

  public Response header(ResponseHeader header) {
    this.header = header;
    return this;
  }

  public ResponseHeader getHeader() {
    return this.header;
  }

  public Response setHeader(ResponseHeader header) {
    this.header = header;
    return this;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      Response response = (Response)o;
      return Objects.equals(this.header, response.header);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[]{this.header});
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": ").append("\"Response\"");
    if (this.header != null) {
      sb.append(", ");
      sb.append("\"header\": ");
      sb.append(this.header);
    }

    sb.append("}");
    return sb.toString();
  }

  public ResponseHeader createResponseHeader() {
    return new ResponseHeader();
  }
}
