package com.ebanking.utils.validation;

import java.util.*;

public class ValidatorContainer<I> implements Validable<I> {

  private final List<Validable<I>> validables = new ArrayList<>();

  public final ValidatorContainer<I> add(Validable<I> validator) {
    validables.add(validator);
    return this;
  }

  @Override
  public final void validate(I input) throws Exception {
    for (Validable<I> validable : validables) {
      validable.validate(input);
    }
  }
}

