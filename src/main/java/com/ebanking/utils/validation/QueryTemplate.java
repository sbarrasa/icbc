package com.ebanking.utils.validation;

public abstract class QueryTemplate<I, RQ, RS> implements Validable {
  private RS response;
  private I inputData;

  public final RS execute(I inputData) throws Exception {
    this.inputData = inputData;
    RQ request = buildRequest(inputData);
    response = get(request);
    validate(response);
    return response;
  }

  public final RS getData() {
    return response;
  }

  public final I getInputData(){
    return inputData;
  }
  protected abstract RQ buildRequest(I inputData);
  protected abstract RS get(RQ request);

  protected final void validate(RS response) throws Exception {
    this.response = response;
    validate();
  }

}
