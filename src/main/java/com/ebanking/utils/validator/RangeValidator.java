package com.ebanking.utils.validator;

import com.ebanking.utils.types.Range;

import java.util.function.Predicate;

public class RangeValidator<T extends Comparable<T>> extends Validator<T> {

  private final Range<T> range;

  public RangeValidator(Range<T> range) {
    this.range = range;
    setExceptionMessage("El valor %s está fuera del rango "
               .concat("[%s,%s]".formatted(range.getLowerBound() ,range.getUpperBound()))
    );
  }

  @Override
  protected Predicate<T> getCondition() {
    return range::contains;
  }
}