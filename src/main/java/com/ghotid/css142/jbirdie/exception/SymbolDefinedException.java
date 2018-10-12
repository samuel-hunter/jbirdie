package com.ghotid.css142.jbirdie.exception;

public class SymbolDefinedException extends LispException {
    public SymbolDefinedException(String symbol) {
        super(String.format("Symbol %s is already defined.", symbol));
    }
}
