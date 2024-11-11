package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.processor.Validable;
import com.ebanking.utils.range.Range;
import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.StrSizeDigitValidator;


public class EntityTypeConverter implements Converter<String, EntityType> {
  private static final String INVALID_ENTITY_CODE = "\"%s no es un código de entidad de AFIP válido";
  public static final Integer CODE_SIZE = 2;

  @Override
  public EntityType convert(String input) throws FiscalException {
    codeValidator.validate(input);

    String code = input.substring(0, CODE_SIZE);

    var entityType = new EntityTypeGetService().get(code);

    notNullValidator.validate(entityType);
    return entityType;
  }

  private static final Validable<Object, FiscalException> notNullValidator = new NotNullValidator<FiscalException>()
      .setExceptionMessage(INVALID_ENTITY_CODE);

  public static final StrSizeDigitValidator<FiscalException> codeValidator
          = new StrSizeDigitValidator<>(new Range<>(2,11), FiscalException::new);

}


