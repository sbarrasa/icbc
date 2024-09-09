package com.ebanking.utils.validation;


public abstract class AbstractValidator<T> implements Validable<T> {

  private T value ;
  private ValidatorExceptionHandler exceptionHandler ;

  public AbstractValidator(){}


  protected ValidatorExceptionHandler exceptionHandler(){
    if(exceptionHandler == null)
      exceptionHandler = Validator.defaultexceptionHandler;

    return this.exceptionHandler;
  }

  public AbstractValidator<T> exceptionHandler(ValidatorExceptionHandler exceptionHandler){
    this.exceptionHandler = exceptionHandler;
    return this;
  }

  public final T getValue(){
    return value;
  }

  public final AbstractValidator<T> setValue(T value){
    this.value = value;
    return this;
  }


  protected Exception buildException(){
    return exceptionHandler().build(this);
  }

  public void validate(T value) throws Exception {
    setValue(value);
    validate();
  }

}
