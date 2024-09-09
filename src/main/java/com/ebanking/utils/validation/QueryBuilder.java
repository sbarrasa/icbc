package com.ebanking.utils.validation;


import java.util.function.Function;
import java.util.function.Predicate;

public class QueryBuilder<I, RQ, RS> extends QueryTemplate<I, RQ, RS> {
  private final AbstractValidator<RS> validator;
  private final Function<I, RQ> requestBuilder;
  private final Function<RQ, RS> dataGetter;


  public QueryBuilder(Function<I, RQ> requestBuilder,
                      Function<RQ, RS> dataGetter,
                      AbstractValidator<RS> validator){

    this.requestBuilder = requestBuilder;
    this.dataGetter = dataGetter;
    this.validator = validator;
  }

  public QueryBuilder(Function<I, RQ> requestBuilder,
                      Function<RQ, RS> dataGetter,
                      Predicate<RS> condition){
    this(requestBuilder, dataGetter, new Validator<>(condition));

  }

  public QueryBuilder(Function<I, RQ> requestBuilder,
                      Function<RQ, RS> dataGetter,
                      Predicate<RS> condition,
                      ValidatorExceptionHandler exceptionHandler){

    this(requestBuilder, dataGetter, condition);
    validator.exceptionHandler(exceptionHandler);

  }

  @Override
  public final RQ buildRequest(I inputData) {
    return requestBuilder.apply(inputData);
  }

  @Override
  public final RS get(RQ request) {
    return dataGetter.apply(request);
  }

  public AbstractValidator<RS> validator() {
    return validator;
  }

  @Override
  public void validate() throws Exception {
    validator.validate(getResponse());
  }
}
