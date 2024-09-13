package com.ebanking.utils.converter;

public interface BidirectionalConverter<T1,T2> extends Converter<T1,T2>{

  T1 to(T2 data) throws Exception;
}
