package com.ebanking.utils.validation;

import java.util.function.Predicate;

public abstract class AbstractValidator<T> implements Validable {

  private T data;
  private String name = "";

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

  public final T getData(){
    return data;
  }

  public final AbstractValidator<T> setData(T data){
    this.data = data;
    return this;
  }

  public final String getName(){
    return name;
  }

  public final AbstractValidator<T> setName(String name){
    this.name = name;
    return this;
  }


  public final void validate(T data) throws Exception {
    setData(data);
    validate();
  }
  @Override
  public void validate() throws Exception {
    if (!getCondition().test(data))
      throw buildException();
  }

  protected abstract Predicate<T> getCondition();

  protected Exception buildException(){
    return exceptionHandler().build(this);
  }


}
