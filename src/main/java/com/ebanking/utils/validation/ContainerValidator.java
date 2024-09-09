package com.ebanking.utils.validation;

import java.util.*;
import java.util.function.Predicate;

public class ContainerValidator<T> extends AbstractValidator<T> {

  private final List<Validable> validables = new ArrayList<>();
  private final List<AbstractValidator<T>> assignableValidators = new ArrayList<>();

  public final List<Validable> validators() {
    return validables;
  }


  public final ContainerValidator<T> add(Validable validator) {
    validables.add(validator);
    return this;
  }

  public final ContainerValidator<T> add(AbstractValidator<T> validator) {
    validables.add(validator);
    assignableValidators.add(validator);
    return this;
  }

  @Override
  protected final Predicate<T> getCondition() {
    return null;
  }


  @Override
  public final void validate() throws Exception {
    assignableValidators.forEach(validator -> validator.setData(this.getData()));

    for (Validable validable : validables) {
        validable.validate();
    }
  }




}

