package com.ebanking.utils.tuple;

public class Join {
  private Join(){}

  public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 obj1, T2 obj2, T3 obj3) {
    return new Triple<>(obj1, obj2, obj3);
  }

  public static <T1, T2> Pair<T1, T2> of(T1 obj1, T2 obj2) {
    return new Pair<>(obj1, obj2);
  }

}
