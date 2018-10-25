package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.InvalidTypeException;

import java.util.List;

public class ConsObject implements LispObject {
    private final LispObject car;
    private final LispObject cdr;

    public ConsObject(LispObject car, LispObject cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public static LispObject fromList(List<LispObject> list) {
        if (list.size() == 0)
            return NilObject.getNil();
        else
            return new ConsObject(
                    list.get(0),
                    fromList(list.subList(1, list.size()))
            );
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
    public LispObject evaluate(Environment environment) {
        LispObject func = car.evaluate(environment);
        if (!(func instanceof FuncObject))
            throw new InvalidTypeException(FuncObject.class, func);

        return ((FuncObject) (func)).call(environment, cdr);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConsObject)) return false;
        ConsObject value = (ConsObject) obj;

        return car.equals(value.car) && cdr.equals(value.cdr);
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
