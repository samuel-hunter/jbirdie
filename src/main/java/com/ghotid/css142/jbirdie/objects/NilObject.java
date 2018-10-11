package com.ghotid.css142.jbirdie.objects;

public class NilObject implements LispObject {
    private static NilObject NIL = new NilObject();

    private NilObject() {
    }

    public static NilObject getNIL() {
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
    public String toString() {
        return "nil";
    }
}
