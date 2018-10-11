package com.ghotid.css142.jbirdie.objects;

public class StringObject implements LispObject {
    private final String value;

    public StringObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return '"' + value + '"';
    }
}
