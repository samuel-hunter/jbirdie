package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispEnvironment;

public abstract class FuncObject implements LispObject {

    public abstract LispObject call(LispEnvironment environment,
                                    LispObject args);

    @Override
    public LispObject getCar() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LispObject getCdr() {
        throw new UnsupportedOperationException();
    }
}
