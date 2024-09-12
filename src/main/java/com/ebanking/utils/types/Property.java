package com.ebanking.utils.types;

import com.ebanking.utils.processor.Pair;

public class Property<V> implements Pair<String,V> {
    private final String name;
    private V value;

    public Property(String name) {
        this.name = name;
    }

    public Property(String name, V value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name;  }

    public void setValue(V value) {this.value = value;}
    public V getValue() { return value; }

    @Override
    public String getFirst() { return getName(); }

    @Override
    public V getSecond() { return getValue(); }
}
