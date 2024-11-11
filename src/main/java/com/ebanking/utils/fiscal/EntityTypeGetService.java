package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.GetService;

import java.util.NavigableMap;
import java.util.TreeMap;

class EntityTypeGetService implements GetService<String, EntityType> {
    private static final NavigableMap<String, EntityType> codeMap = new TreeMap<>();
    static {
        codeMap.put("20", EntityType.UNIPERSONAL);
        codeMap.put("23", EntityType.UNIPERSONAL);
        codeMap.put("24", EntityType.UNIPERSONAL);
        codeMap.put("27", EntityType.UNIPERSONAL);
        codeMap.put("30", EntityType.COMPANY);
        codeMap.put("50", EntityType.COMPANY);
        codeMap.put("90", EntityType.COMPANY);
    }

    @Override
    public EntityType get(String input)  {
        var fCode = codeMap.floorEntry(input);
        return fCode != null ? fCode.getValue() : null;
    }
}
