package com.ghotid.css142.jbirdie.objects;

import java.util.Iterator;
import com.ghotid.css142.jbirdie.LispEnvironment;

public interface LispObject {
    LispObject getCar();
    LispObject getCdr();

    default LispObject evaluate(LispEnvironment environment) {
        return this;
    }
}
