package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.environment.Environment;

public class SymbolObject extends AtomObject {
    private final String symbol;

    // Constants
    private static final SymbolObject T = new SymbolObject("t");

    protected SymbolObject(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Ensure that nil is returned properly.
     */
    public static SymbolObject fromString(String symbol) {
        switch (symbol) {
            case "nil":
                return NilObject.getNil();
            case "t":
                return T;
            default:
                return new SymbolObject(symbol);
        }
    }

    public static SymbolObject getT() {
        return T;
    }

    public static SymbolObject fromBoolean(boolean isTruthy) {
        if (isTruthy)
            return T;
        else
            return NilObject.getNil();
    }

    @Override
    public String getValue() {
        return symbol;
    }

    @Override
    public LispObject evaluate(Environment environment) {
        return environment.get(symbol);
    }
}
