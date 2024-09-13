package com.ebanking.utils.types;

public class Range<T extends Comparable<T>> implements Pair<T, T> {
  private final T lowerBound;
  private final T upperBound;

  public Range(T lowerBound, T upperBound) {
    if (lowerBound.compareTo(upperBound) > 0) {
      throw new IllegalArgumentException("El mínimo no debe superar al máximo");
    }
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  public T getLowerBound() {
    return lowerBound;
  }

  public T getUpperBound() {
    return upperBound;
  }

  @Override
  public T getData1() {
    return getLowerBound();
  }

  @Override
  public T getData2() {
    return getUpperBound();
  }

  public boolean contains(T value) {
    return value.compareTo(lowerBound) >= 0 && value.compareTo(upperBound) <= 0;
  }

  @Override
  public String toString() {
    return String.format("Range[%s, %s]", lowerBound, upperBound);
  }
}

