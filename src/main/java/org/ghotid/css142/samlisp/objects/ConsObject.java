package org.ghotid.css142.samlisp.objects;

public class ConsObject implements LispObject {
    private final LispObject car;
    private final LispObject cdr;

    public ConsObject(LispObject car, LispObject cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    @Override
    public LispObject getCar() {
        return car;
    }

    @Override
    public LispObject getCdr() {
        return cdr;
    }

    @Override
    public String toString() {
        return "(" + car.toString() + " . " + cdr.toString() + ")";
    }

    @Override
    public String inspect() {
        return "(" + car.inspect() + " . " + cdr.inspect() + ")";
    }
}
