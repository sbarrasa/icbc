package com.ebanking.utils.validation;

import java.util.function.Predicate;

public abstract class AbstractValidator<T> implements Validable {

  private T data;
  private String name = "";

  private ValidatorExceptionFunction exceptionFunction ;
  public AbstractValidator(){}


  protected ValidatorExceptionFunction exceptionFunction(){
    if(exceptionFunction == null)
      exceptionFunction = Validator.defaultExceptionFunction;

    return this.exceptionFunction;
  }

  public AbstractValidator<T> exceptionFunction(ValidatorExceptionFunction exceptionFunction){
    this.exceptionFunction = exceptionFunction;
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

  protected abstract Predicate<T> getCondition();
  protected Exception buildException(){
    return exceptionFunction().apply(this);
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

  public void assign(AbstractValidator<T> other) {
    this.exceptionFunction(other.exceptionFunction());
    this.setName(other.getName());
    this.setData(other.getData());
  }
}
