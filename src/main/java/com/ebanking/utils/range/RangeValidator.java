package com.ebanking.utils.range;

import com.ebanking.utils.validator.Validator;

import java.util.function.Predicate;

public class RangeValidator<T extends Comparable<T>> extends Validator<T, OutOfRangeException> {

  private final Range<T> range;
  public static String DEFAULT_MESSAGE = "El valor %s está fuera del rango [%s,%s]";

  public RangeValidator(Range<T> range) {
    this.range = range;
    setExceptionMessage(DEFAULT_MESSAGE);
    setExceptionHandler(OutOfRangeException::new);
  }

  @Override
  public String createExceptionMessage(T value){
    return getExceptionMessage().formatted(
                            value,
                            this.range.getMin(),
                            range.getMax()
    );
  }

  @Override
  protected Predicate<T> getCondition() {
    return range::contains;
  }
}