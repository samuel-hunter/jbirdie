package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.environment.Environment;

public class SymbolObject implements LispObject {
    private final String symbol;

    public SymbolObject(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return symbol;
    }

    @Override
    public LispObject evaluate(Environment environment) {
        return environment.get(symbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SymbolObject)) return false;
        return symbol.equals(((SymbolObject) obj).symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
