package org.ghotid.css142.birdie.objects;

public class NilObject implements LispObject {
    private static NilObject nilObject = new NilObject();

    private NilObject() {
    }

    public static NilObject getNilObject() {
        return nilObject;
    }

    @Override
    public LispObject getCar() {
        return nilObject;
    }

    @Override
    public LispObject getCdr() {
        return nilObject;
    }

    @Override
    public String toString() {
        return "nil";
    }

    @Override
    public String inspect() {
        return "nil";
    }
}
