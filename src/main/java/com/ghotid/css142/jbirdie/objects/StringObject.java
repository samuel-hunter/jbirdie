package com.ghotid.css142.jbirdie.objects;

public class StringObject extends AtomObject {
    private final String value;

    public StringObject(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String inspect() {
        return '"' + value + '"';
    }
}
