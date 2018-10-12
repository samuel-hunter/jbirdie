package com.ghotid.css142.jbirdie.objects;

public class NilObject implements LispObject {
    private static final NilObject NIL = new NilObject();

    private NilObject() {
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

    @Override
    public String toString() {
        return "nil";
    }
}
