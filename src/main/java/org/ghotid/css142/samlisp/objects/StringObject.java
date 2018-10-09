package org.ghotid.css142.samlisp.objects;

public class StringObject implements LispObject {
    private final String value;

    public StringObject(String value) {
        this.value = value;
    }

    @Override
    public LispObject getCar() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LispObject getCdr() {
        throw new UnsupportedOperationException();
    }

    public String getValue() {
        return value;
    }

    public SymbolObject toSymbolObject() {
        return new SymbolObject(value);
    }

    public String toString() {
        return value;
    }

    public String inspect() {
        return '"' + value + '"';
    }
}
