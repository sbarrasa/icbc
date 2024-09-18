package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.validator.NotEmptyValidator;
import com.ebanking.utils.validator.Validator;

public class CuitConverter implements Converter<String, Cuit> {

  static final Validator<String[]> partsCountValidator = Validator
      .<String[]>build(parts -> parts.length == 3)
      .setExceptionMessage("Formato de CUIT inválido");

  static final Validator<String> sizeValidator = Validator
      .<String>build(cuit -> cuit.length() == 11)
      .setExceptionMessage("El CUIT debe tener 11 dígitos");

  private static final Validator<String> notEmptyValidator = new NotEmptyValidator()
      .setExceptionMessage("El CUIT no puede ser nulo o vacío");


  @Override
  public Cuit convert(String cuitStr) throws Exception {
    notEmptyValidator.validate(cuitStr);

    if (cuitStr.contains(Cuit.DEFAULT_SEPARATOR)) {
      return parse(cuitStr, Cuit.DEFAULT_SEPARATOR);
    } else {
      return parse(cuitStr);
    }
  }

  private Cuit parse(String cuitStr, String separator) throws Exception {
    String[] parts = cuitStr.split(separator);
    partsCountValidator.validate(parts);
    return Cuit.of(
                    parts[0],
                    parts[1],
                    parts[2]
        ).showSeparator(true);
  }

  private Cuit parse(String cuitStr) throws Exception {
    sizeValidator.validate(cuitStr);
    return Cuit.of(
        cuitStr.substring(0, 2),
        cuitStr.substring(2, 10),
        cuitStr.substring(10)
    ).showSeparator(false);
  }
}
