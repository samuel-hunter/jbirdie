package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.InvalidTypeException;

public interface LispObject {
    default LispObject getCar() {
        throw new InvalidTypeException("List", this);
    }

    default LispObject getCdr() {
        throw new InvalidTypeException("List", this);
    }

    default LispObject evaluate(LispEnvironment environment) {
        return this;
    }
}
