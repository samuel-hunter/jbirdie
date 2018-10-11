package com.ghotid.css142.jbirdie.exception;

public class ArgumentNumberError extends LispException {
    public ArgumentNumberError(Integer given, String expected) {
        super(String.format(
                "Wrong number of arguments (given %d, expected %s).",
                given, expected
        ));
    }
}
