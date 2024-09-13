package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.processor.DigitValidator;
import com.ebanking.utils.processor.NotNullValidator;
import com.ebanking.utils.processor.Validator;


public enum EntityType {
    UNIPERSONAL,
    COMPANY;

    private static final String INVALID_ENTITY_CODE = "\"%s no es un código de entidad de AFIP válido";
    public static final Integer codeSize = 2;

    public static final Converter<String, EntityType> codeConverter = new Converter<>() {
        @Override
        public EntityType convert(String input) throws Exception {
            codeValidator.validate(input);

            String code = input.substring(0, codeSize);

            var entityType =  entityTypes.get(code);

            notNullValidator.validate(entityType);
            return entityType;
        }

    };

    private static final Validator<Object> notNullValidator = new NotNullValidator()
                    .setExceptionMessage(INVALID_ENTITY_CODE);

    public static final Validator<String> codeValidator = new DigitValidator()
                    .setMinSize(codeSize)
                    .setExceptionMessage(INVALID_ENTITY_CODE);

    public static final EntityTypeService entityTypes = new EntityTypeService();
}

