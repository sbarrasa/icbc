package com.ebanking.utils.validation;


import java.util.function.Function;
import java.util.function.Predicate;

public class QueryBuilder<D, RQ, RS> extends QueryTemplate<D, RQ, RS> {
  private final AbstractValidator<RS> validator;
  private final Function<D, RQ> requestBuilder;
  private final Function<RQ, RS> responseGetter;


  public QueryBuilder(Function<D, RQ> requestBuilder,
                      Function<RQ, RS> responseGetter,
                      AbstractValidator<RS> validator){

    this.requestBuilder = requestBuilder;
    this.responseGetter = responseGetter;
    this.validator = validator;
  }

  public QueryBuilder(Function<D, RQ> requestBuilder,
                      Function<RQ, RS> responseGetter,
                      Predicate<RS> condition){
    this(requestBuilder, responseGetter, new Validator<>(condition));

  }

  public QueryBuilder(Function<D, RQ> requestBuilder,
                      Function<RQ, RS> responseGetter,
                      Predicate<RS> condition,
                      Function<String, ? extends Exception> exceptionFunction){

    this(requestBuilder, responseGetter, condition);
    ((Validator<RS>)validator)
            .exceptionFunction(exceptionFunction)
            .messageBuilder(() -> "%s -> %s (err)".formatted(getInputData(), getData()));

  }

  @Override
  public final RQ buildRequest(D inputData) {
    return requestBuilder.apply(inputData);
  }

  @Override
  public final RS get(RQ request) {
    return responseGetter.apply(request);
  }


  public AbstractValidator<RS> validator() {
    return validator;
  }

  @Override
  public void validate() throws Exception {
    validator.validate(getData());
  }
}
