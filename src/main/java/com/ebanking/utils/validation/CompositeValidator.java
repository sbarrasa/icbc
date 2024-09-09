package com.ebanking.utils.validation;

import java.util.*;
import java.util.function.Predicate;

public class CompositeValidator<T> extends AbstractValidator<T> {

  private Validable validatorFail;
  private final List<Validable> validators = new ArrayList<>();
  private final List<AbstractValidator<T>> abstractValidators = new ArrayList<>();

  public final List<Validable> validators() {
    return validators;
  }

  public final CompositeValidator<T> add(Validable validator) {
    validators.add(validator);
    return this;
  }

  public final CompositeValidator<T> add(AbstractValidator<T> validator) {
    validators.add(validator);
    abstractValidators.add(validator);
    return this;
  }

  @Override
  public Predicate<T> getCondition() {
    return null;
  }

  @Override
  public final void validate() throws Exception {
    abstractValidators.forEach(abstractValidator -> abstractValidator.setData(this.getData()));

    for (Validable validator : validators) {
      try{
        validator.validate();
      } catch (Exception e){
        validatorFail = validator;
        throw e;
      }
    }
  }

  public final Validable getValidatorFail() {
    return validatorFail;
  }


}

