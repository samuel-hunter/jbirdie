package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public class StringObject extends AtomObject {
    private final String value;

    public StringObject(LispSource source, String value) {
        super(source);
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
