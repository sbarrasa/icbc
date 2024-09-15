package com.ebanking.utils.fiscal;


import com.ebanking.utils.validator.NotNullValidator;
import com.ebanking.utils.validator.Validator;

import java.util.Objects;

public abstract class Cuit {
    public static final String DEFAULT_SEPARATOR = "-";

    private static final Validator<Object> idValidator = new NotNullValidator().setExceptionMessage("El ID no puede ser nulo");
    private static final Validator<Object> dvValidator = new NotNullValidator().setExceptionMessage("El dígito verificador no puede ser nulo");

    public abstract String getEntityTypeCode();
    public abstract String getId();
    public abstract String getVerificationDigit();


    private boolean showSeparator=false;

    public EntityType getEntityType() throws Exception {
        return EntityType.codeConverter.convert(this.getEntityTypeCode());
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


    public static Cuit of(String cuitStr) throws Exception {
        return new CuitConverter().convert(cuitStr);

    }


    public static Cuit of(String entityTypeCode, String id, String verificationDigit) throws Exception {
        EntityType.codeValidator.validate(entityTypeCode);
        idValidator.validate(id);
        dvValidator.validate(verificationDigit);

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


    @Override
    public String toString(){
        var separator = showSeparator() ? DEFAULT_SEPARATOR : "";
        return String.join(separator,
            this.getEntityTypeCode(),
            this.getId(),
            this.getVerificationDigit());
    }

    public boolean showSeparator() {
        return showSeparator;
    }

    public Cuit showSeparator(boolean separator) {
        this.showSeparator = separator;
        return this;
    }

}
