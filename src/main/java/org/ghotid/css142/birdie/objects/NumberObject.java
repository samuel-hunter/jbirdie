package org.ghotid.css142.birdie.objects;

public class NumberObject implements LispObject {
    private final Double value;

    public NumberObject(Double value) {
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

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String inspect() {
        return toString();
    }
}
