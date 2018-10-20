package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.environment.Environment;

public abstract class FuncObject extends AtomObject {

    public abstract LispObject call(Environment environment, LispObject args);

    @Override
    public Class getValue() {
        return getClass();
    }
}
