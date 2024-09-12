package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.processor.Validator;

import java.util.NavigableMap;
import java.util.TreeMap;

public enum EntityType {
    UNIPERSONAL,
    COMPANY;


    public static final NavigableMap<String, EntityType> codeMap = new TreeMap<>() {{
        put("20", EntityType.UNIPERSONAL);
        put("23", EntityType.UNIPERSONAL);
        put("24", EntityType.UNIPERSONAL);
        put("27", EntityType.UNIPERSONAL);
        put("30", EntityType.COMPANY);
        put("50", EntityType.COMPANY);
        put("90", EntityType.COMPANY);
    }};

    public static final Converter<String, EntityType> converter = new Converter<>() {

        @Override
        public EntityType convert(String input) throws Exception {
            String code = codeConverter.convert(input);

            var fValue = codeMap.floorEntry(code);
            return fValue != null ? fValue.getValue() : null;
        }

    };

    public static final Validator<String> codeValidator = Validator.build(Validator.isDigits)
                        .setExceptionMessage("Código de entidad de AFIP incorrecto");

    public static final Converter<String, String> codeConverter = input -> {
        codeValidator.validate(input);
        return input.substring(0, 2);
    };
}

