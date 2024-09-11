package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.processor.Validator;

import java.util.Objects;

public abstract class Cuit {
    public static String INVALID_CUIT_NULL = "El CUIT no puede ser nulo";
    public static String INVALID_CUIT_SIZE = "El CUIT debe tener 11 dígitos";
    public static String INVALID_CUIT_FORMAT = "Formato de CUIT inválido";
    private static final Validator<Object> notNullValidator = Validator
            .build(Objects::nonNull)
            .exceptionMessageHandler(INVALID_CUIT_NULL::formatted);
    private static final Validator<String[]> partsValidator = Validator
            .<String[]>build(parts -> parts.length == 3)
            .exceptionMessageHandler((value) -> INVALID_CUIT_FORMAT);

    private static final Validator<String> invalidSizeValidator = Validator
            .<String>build(cuit -> cuit.length() == 11)
            .exceptionMessageHandler(cuit -> INVALID_CUIT_SIZE);

    public static final String SEPARATOR = "-";

    public abstract Integer getEntityTypeCode();
    public abstract Integer getId();
    public abstract Integer getVerificationDigit();

    public static final StringConverter converter = new StringConverter();

    @Override
    public String toString() {
        return String.join(SEPARATOR,
                String.valueOf(getEntityTypeCode()),
                String.valueOf(getId()),
                String.valueOf(getVerificationDigit()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuit cuit = (Cuit) o;
        return getEntityTypeCode().equals(cuit.getEntityTypeCode()) &&
                getId().equals(cuit.getId()) &&
                getVerificationDigit().equals(cuit.getVerificationDigit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntityTypeCode(), getId(), getVerificationDigit());
    }

    public EntityType getEntityType() {
        return EntityTypeConverter.instance.convert(this.getEntityTypeCode());
    }


    public static class StringConverter implements Converter<String, Cuit>{
        @Override
        public Cuit convert(String cuit) throws Exception {
            notNullValidator.validate(cuit);
            if (cuit.contains(SEPARATOR)) {
                return parse(cuit, SEPARATOR);
            } else {
                return parse(cuit);
            }
        }

        private Cuit parse(String cuit, String separator) throws Exception {
           String[] parts = cuit.split(separator);

           partsValidator.validate(parts);

           return Cuit.of(parts[0], parts[1], parts[2]);
        }

        private Cuit parse(String cuit) throws Exception {
            invalidSizeValidator.validate(cuit);

            return Cuit.of(Integer.parseInt(cuit.substring(0, 2)),
                            Integer.parseInt(cuit.substring(2, 10)),
                            Integer.parseInt(cuit.substring(10))
            );

        }

    }

    public static Cuit of(String cuit) throws Exception {
        return converter.convert(cuit);
    }


    public static Cuit of(String entityTypeCode, String id, String verificationDigit) throws Exception {
        return of(Integer.parseInt(entityTypeCode),
                Integer.parseInt(id),
                Integer.parseInt(verificationDigit));
    }

    public static Cuit of(Integer entityTypeCode, Integer id, Integer verificationDigit) throws Exception {
        notNullValidator.validate(entityTypeCode);
        notNullValidator.validate(id);
        notNullValidator.validate(verificationDigit);
        return new Cuit() {
            @Override
            public Integer getEntityTypeCode() {
                return entityTypeCode;
            }

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public Integer getVerificationDigit() {
                return verificationDigit;
            }
        };
    } 

}
