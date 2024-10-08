package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.StringDigitValidator;
import com.ebanking.utils.validator.Validator;

public class EntityTypeConverter implements Converter<String, EntityType> {
  private static final String INVALID_ENTITY_CODE = "\"%s no es un código de entidad de AFIP válido";
  public static final Integer codeSize = 2;

  @Override
  public EntityType convert(String input) throws Exception {
    codeValidator.validate(input);

    String code = input.substring(0, codeSize);

    var entityType = entityTypes.get(code);

    notNullValidator.validate(entityType);
    return entityType;
  }

  private static final Validator<Object> notNullValidator = new NotNullValidator()
      .setExceptionMessage(INVALID_ENTITY_CODE);

  public static final StringDigitValidator codeValidator = new StringDigitValidator() {
    {
      setExceptionMessage(INVALID_ENTITY_CODE);
      getSize().setMin(codeSize);
    }
  };

  public static final EntityTypeService entityTypes = new EntityTypeService();
}