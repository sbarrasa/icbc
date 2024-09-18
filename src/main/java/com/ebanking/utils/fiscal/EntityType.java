package com.ebanking.utils.fiscal;


public enum EntityType {
    UNIPERSONAL,
    COMPANY;


    public static final EntityTypeConverter codeConverter = new EntityTypeConverter();
}

