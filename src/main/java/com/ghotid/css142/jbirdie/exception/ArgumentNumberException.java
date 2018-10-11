package com.ghotid.css142.jbirdie.exception;

public class ArgumentNumberException extends LispException {
    public ArgumentNumberException(Integer given, String expected) {
        super(String.format(
                "Wrong number of arguments (given %d, expected %s).",
                given, expected
        ));
    }
}
