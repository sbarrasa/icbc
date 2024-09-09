package com.ebanking.utils.validation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractValidator<T> implements Validable {
  private T data;
  private String name = "";

  private Function<String, ? extends Exception> exceptionFunction ;


  public Function<String, ? extends Exception> exceptionFunction(){
    if(exceptionFunction == null)
      exceptionFunction = ValidatorException::new;

    return this.exceptionFunction;
  }

  public AbstractValidator<T> exceptionFunction(Function<String, ? extends Exception> exceptionFunction){
    this.exceptionFunction = exceptionFunction;
    return this;
  }
  public AbstractValidator(){}

  public Supplier<String> messageBuilder() {
    return () -> "error de validación (%s)".formatted(getName());
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
    return exceptionFunction().apply(messageBuilder().get());
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
