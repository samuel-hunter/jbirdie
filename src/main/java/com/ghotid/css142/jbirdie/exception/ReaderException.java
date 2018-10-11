package com.ghotid.css142.jbirdie.exception;

public class ReaderException extends LispException {
    private final Integer line;

    public ReaderException(Integer line, String message) {
        super(message);
        this.line = line;
    }

    public Integer getLine() {
        return line;
    }
}
