package org.ghotid.css142.birdie.objects;

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
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(car.toString());

        LispObject tail = cdr;

        while (tail instanceof ConsObject) {
            sb.append(' ').append(tail.getCar().toString());
            tail = tail.getCdr();
        }

        if (!(tail instanceof NilObject)) {
            sb.append(" . ").append(tail.toString());
        }

        return sb.append(')').toString();
    }
}
