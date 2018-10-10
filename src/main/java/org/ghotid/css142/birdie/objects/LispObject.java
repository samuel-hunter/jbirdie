package org.ghotid.css142.birdie.objects;

import org.ghotid.css142.birdie.LispEnvironment;

public interface LispObject {
    LispObject getCar();
    LispObject getCdr();

    default LispObject evaluate(LispEnvironment environment) {
        return this;
    }

    String toString();
}
