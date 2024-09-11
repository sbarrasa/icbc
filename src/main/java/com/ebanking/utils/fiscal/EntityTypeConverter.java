package com.ebanking.utils.fiscal;

import com.ebanking.utils.processor.Converter;

import java.util.*;
import java.util.stream.Collectors;


public class EntityTypeConverter implements Converter<Integer, EntityType> {

    private static EntityTypeConverter instance;

    public static final NavigableMap<Integer, EntityType> map;

    static {
        map = new TreeMap<>();
        map.put(20, EntityType.UNIPERSONAL);
        map.put(23, EntityType.UNIPERSONAL);
        map.put(24, EntityType.UNIPERSONAL);
        map.put(27, EntityType.UNIPERSONAL);
        map.put(30, EntityType.COMPANY);
        map.put(50, EntityType.COMPANY);
        map.put(90, EntityType.COMPANY);

    }

    @Override
    public EntityType convert(Integer input) {
        var fValue = map.floorEntry(input);
        return fValue != null ? fValue.getValue() : null;
    }

    public Integer convert(EntityType entityType) {
        return getCodes(entityType).stream().findFirst().orElse(null);
    }

    public List<Integer> getCodes(EntityType entityType) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(entityType))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static EntityTypeConverter getInstance() {
       if(instance == null)
           instance = new EntityTypeConverter();
       return instance;
    }

}
