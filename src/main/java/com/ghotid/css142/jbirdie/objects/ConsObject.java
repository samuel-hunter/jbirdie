package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispEnvironment;

import java.util.Iterator;

public class ConsObject implements LispObject, Iterable<LispObject> {
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
    public LispObject evaluate(LispEnvironment environment) {
        LispObject func = car.evaluate(environment);
        if (!(func instanceof FuncObject))
            throw new UnsupportedOperationException();

        return ((FuncObject) (func)).call(environment, cdr);
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

    @Override
    public Iterator<LispObject> iterator() {
        final LispObject startingTail = this;

        return new Iterator<LispObject>() {
            private LispObject tail = startingTail;

            @Override
            public boolean hasNext() {
                if (tail instanceof ConsObject)
                    return true;
                else if (tail instanceof NilObject)
                    return false;
                else
                    throw new IllegalArgumentException();
            }

            @Override
            public LispObject next() {
                LispObject next = tail.getCar();
                tail = tail.getCdr();
                return next;
            }
        };
    }
}
