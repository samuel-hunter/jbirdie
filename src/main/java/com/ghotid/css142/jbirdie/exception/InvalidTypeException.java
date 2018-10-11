package com.ghotid.css142.jbirdie.exception;

import com.ghotid.css142.jbirdie.objects.LispObject;

public class InvalidTypeException extends LispException {
    public InvalidTypeException(Class<? extends LispObject> expected,
                                LispObject actual) {
        this(expected.getSimpleName(), actual);
    }

    public InvalidTypeException(String expected,
                                LispObject actual) {
        super(String.format(
                "The value %s is not of type %s.",
                actual.toString(), expected
        ));
    }
}
