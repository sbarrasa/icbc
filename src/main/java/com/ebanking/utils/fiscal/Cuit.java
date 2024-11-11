package com.ebanking.utils.fiscal;


import com.ebanking.utils.range.Range;
import com.ebanking.utils.validator.StrSizeDigitValidator;

import java.io.Serializable;
import java.util.Objects;

public abstract class Cuit implements Serializable {
    public static final String DEFAULT_SEPARATOR = "-";

    private static final StrSizeDigitValidator<FiscalException> entityCodeValidator =
            new StrSizeDigitValidator<>(new Range<>(2,2), FiscalException::new);

    private static final StrSizeDigitValidator<FiscalException> idValidator =
            new StrSizeDigitValidator<>(new Range<>(1,8), FiscalException::new);

    private static final StrSizeDigitValidator<FiscalException> dvValidator =
        new StrSizeDigitValidator<>(new Range<>(1,1), FiscalException::new);


    public abstract String getentityCode();
    public abstract String getId();
    public abstract String getVerificationDigit();


    private boolean showSeparator=false;

    public EntityType getEntityType() throws FiscalException {
        return EntityType.codeConverter.convert(this.getentityCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuit cuit = (Cuit) o;
        return getentityCode().equals(cuit.getentityCode()) &&
                getId().equals(cuit.getId()) &&
                getVerificationDigit().equals(cuit.getVerificationDigit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getentityCode(), getId(), getVerificationDigit());
    }


    public static Cuit of(String cuitStr) throws FiscalException {
        return new CuitConverter().convert(cuitStr);
    }


    public static Cuit of(String entityCode, String id, String verificationDigit) throws FiscalException {
        entityCodeValidator.validate(entityCode);
        idValidator.validate(id);
        dvValidator.validate(verificationDigit);

        return new Cuit() {
            @Override
            public String getentityCode() {
                return entityCode;
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
            this.getentityCode(),
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
