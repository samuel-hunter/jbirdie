package org.ghotid.css142.birdie.objects;

import org.ghotid.css142.birdie.LispEnvironment;

public class SymbolObject implements LispObject {
    private final String symbol;

    public SymbolObject(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return symbol;
    }

    public StringObject toStringObject() {
        return new StringObject(symbol);
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
    public LispObject evaluate(LispEnvironment environment) {
        return environment.getVariable(symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
