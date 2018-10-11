package com.ghotid.css142.jbirdie.exception;

public class UndefinedSymbolException extends LispException {
    public UndefinedSymbolException(String symbol) {
        super("Symbol " + symbol + " is undefined.");
    }
}
