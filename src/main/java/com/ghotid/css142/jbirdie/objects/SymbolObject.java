package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.LispSource;

public class SymbolObject extends AtomObject {
    private final String symbol;

    // Constants
    private static final SymbolObject T =
            new SymbolObject(LispSource.BUILTIN_SOURCE, "t");

    protected SymbolObject(LispSource source, String symbol) {
        super(source);
        this.symbol = symbol;
    }

    /**
     * Ensure that nil is returned properly.
     */
    public static SymbolObject fromString(LispSource source, String symbol) {
        switch (symbol) {
            case "nil":
                return NilObject.getNil();
            case "t":
                return T;
            default:
                return new SymbolObject(source, symbol);
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
    public LispResult evaluate(InterpreterContext context) {
        return new LispResult(context.getEnvironment().get(symbol), true);
    }
}
