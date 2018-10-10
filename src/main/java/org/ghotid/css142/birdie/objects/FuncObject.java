package org.ghotid.css142.birdie.objects;

import org.ghotid.css142.birdie.LispEnvironment;

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
