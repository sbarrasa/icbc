package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Service;

import java.util.NavigableMap;
import java.util.TreeMap;

public class EntityTypeService implements Service<String, EntityType> {
    public static final NavigableMap<String, EntityType> codeMap = new TreeMap<>() {{
        put("20", EntityType.UNIPERSONAL);
        put("23", EntityType.UNIPERSONAL);
        put("24", EntityType.UNIPERSONAL);
        put("27", EntityType.UNIPERSONAL);
        put("30", EntityType.COMPANY);
        put("50", EntityType.COMPANY);
        put("90", EntityType.COMPANY);
    }};

    @Override
    public EntityType get(String input)  {
        var fCode = codeMap.floorEntry(input);
        return fCode != null ? fCode.getValue() : null;
    }
}
