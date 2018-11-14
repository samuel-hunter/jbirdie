package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public class NumberObject extends AtomObject {
    private final double value;

    public NumberObject(LispSource source, double value) {
        super(source);
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}
