package org.ghotid.css142.birdie.objects;

import org.ghotid.css142.birdie.LispEnvironment;

public class StringObject implements LispObject {
    private final String value;

    public StringObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public SymbolObject toSymbolObject() {
        return new SymbolObject(value);
    }

    @Override
    public LispObject getCar() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LispObject getCdr() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return '"' + value + '"';
    }
}
