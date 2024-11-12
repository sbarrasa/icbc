package com.ebanking.utils.range;

public class Range<T extends Comparable<T>>  {

  private final T min;
  private final T max;

  public Range(T min, T max) {
    if (min.compareTo(max) > 0) {
      throw new IllegalArgumentException("El mínimo no debe superar al máximo");
    }
    this.min = min;
    this.max = max;
  }

  public T getMin() {
    return min;
  }

  public T getMax() {
    return max;
  }




  public boolean contains(T value) {
    return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
  }

  @Override
  public String toString() {
    return String.format("Range[%s,%s]", min, max);
  }
}

