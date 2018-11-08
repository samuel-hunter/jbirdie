package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;

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
    public LispResult evaluate(InterpreterContext context) {
        LispObject func = context.evaluate(car);

        return LispObject.cast(
                FuncObject.class,
                func
        ).call(context, cdr);
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
        sb.append('(').append(car.inspect());

        LispObject tail = cdr;

        while (tail instanceof ConsObject) {
            sb.append(' ').append(tail.getCar().inspect());
            tail = tail.getCdr();
        }

        if (!(tail instanceof NilObject)) {
            sb.append(" . ").append(tail.inspect());
        }

        return sb.append(')').toString();
    }
}
