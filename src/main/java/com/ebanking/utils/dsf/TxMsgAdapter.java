package com.ebanking.utils.dsf;

import com.ebanking.dsf.Header;

public class TxMsgAdapter {
  private final Header header;
  private final Object data;
  private final boolean dataIsAssigned;

  public TxMsgAdapter(Header header){
    this.header = header;
    this.data = null;
    this.dataIsAssigned = false;

  }

  public TxMsgAdapter(Header header, Object data){
    this.header = header;
    this.data = data;
    this.dataIsAssigned = true;
  }

  public Object getData() {
    return data;
  }

  public Header getHeader() {
    return header;
  }

  public boolean isDataIsAssigned() {
    return dataIsAssigned;
  }

}
