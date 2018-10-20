package com.ghotid.css142.jbirdie.objects;

public class NumberObject extends AtomObject {
    private final double value;

    public NumberObject(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}
