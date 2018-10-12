package com.ghotid.css142.jbirdie.exception;

public class SymbolConstantException extends LispException {
    public SymbolConstantException(String symbol) {
        super(String.format("%s is constant and can't be set.", symbol));
    }
}
