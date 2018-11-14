package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public final class NilObject extends SymbolObject {
    private static final NilObject NIL = new NilObject();

    private NilObject() {
        super(LispSource.BUILTIN_SOURCE, "nil");
    }

    public static NilObject getNil() {
        return NIL;
    }

    @Override
    public LispObject getCar() {
        return NIL;
    }

    @Override
    public LispObject getCdr() {
        return NIL;
    }

    @Override
    public boolean isTruthy() {
        return false;
    }
}
