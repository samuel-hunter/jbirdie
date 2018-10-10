package org.ghotid.css142.birdie.objects;

public class SymbolObject implements LispObject {
    private final String symbol;

    public SymbolObject(String symbol) {
        this.symbol = symbol;
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
        return symbol;
    }

    public StringObject toStringObject() {
        return new StringObject(symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public String inspect() {
        return toString();
    }
}
