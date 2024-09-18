package com.ebanking.utils.validator;

import com.ebanking.utils.types.Range;

import java.util.function.Predicate;

public class RangeValidator<T extends Comparable<T>> extends Validator<T> {

  private final Range<T> range;


  public RangeValidator(Range<T> range) {
    this.range = range;
    setExceptionMessage("El valor %s está fuera del rango [%s,%s]");
    setExceptionHandler((message, value) -> new Exception(createMessage(message, value)));
  }

  private String createMessage(String message, T value){
    return message.formatted(
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