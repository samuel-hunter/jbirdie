package com.ghotid.css142.jbirdie.objects;

public final class NilObject extends SymbolObject {
    private static final NilObject NIL = new NilObject();

    private NilObject() {
        super("nil");
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
