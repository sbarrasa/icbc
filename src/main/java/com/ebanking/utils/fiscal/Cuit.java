package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;
import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.Validator;

import java.util.Objects;

public abstract class Cuit {

    public static final String SEPARATOR = "-";

    private static final Validator<Object> notNullValidator = new NotNullValidator()
            .setExceptionMessage("El CUIT no puede ser nulo");

    public static final Validator<String[]> partsCountValidator = Validator
            .<String[]>build(parts -> parts.length == 3)
            .setExceptionMessage("Formato de CUIT inválido");

    public static final Validator<String> sizeValidator = Validator
            .<String>build(cuit -> cuit.length() == 11)
            .setExceptionMessage("El CUIT debe tener 11 dígitos");


    public abstract String getEntityTypeCode();
    public abstract String getId();
    public abstract String getVerificationDigit();

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

    public EntityType getEntityType() throws Exception {
        return EntityType.codeConverter.convert(this.getEntityTypeCode());
    }

    public static Cuit of(String cuit) throws Exception {
        return stringConverter.convert(cuit);

    }

    public String toStringPlain(){
        return getEntityTypeCode()+getId()+getVerificationDigit();
    }

    public static Cuit of(String entityTypeCode, String id, String verificationDigit) throws Exception {
        EntityType.codeValidator.validate(entityTypeCode);
        notNullValidator.validate(id);
        notNullValidator.validate(verificationDigit);

        return new Cuit() {
            @Override
            public String getEntityTypeCode() {
                return entityTypeCode;
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getVerificationDigit() {
                return verificationDigit;
            }
        };
    }

    private static final Converter<String, Cuit> stringConverter = new Converter<>(){
        @Override
        public Cuit convert(String cuit) throws Exception {
            notNullValidator.validate(cuit);
            if (cuit.contains(SEPARATOR)) {
                return parse(cuit, SEPARATOR);
            } else {
                return parse(cuit);
            }
        }

        private Cuit parse(String cuit, String separator)  throws Exception {
            String[] parts = cuit.split(separator);

            partsCountValidator.validate(parts);

            return Cuit.of(parts[0], parts[1], parts[2]);
        }

        private Cuit parse(String cuit) throws Exception {
            sizeValidator.validate(cuit);

            return Cuit.of(cuit.substring(0, 2),
                    cuit.substring(2, 10),
                    cuit.substring(10)
            );
        }

    };

}
