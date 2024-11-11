package com.ebanking.utils.processor;


public interface Transformer<I,O>  {
  O transform(I input) throws Exception;
}
