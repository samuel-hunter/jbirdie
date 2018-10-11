package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispEnvironment;

public class SymbolObject implements LispObject {
    private final String symbol;

    public SymbolObject(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return symbol;
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
