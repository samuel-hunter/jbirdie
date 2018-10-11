package com.ghotid.css142.jbirdie.objects;

public class NumberObject implements LispObject {
    private final Double value;

    public NumberObject(Double value) {
        assert value != null;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}