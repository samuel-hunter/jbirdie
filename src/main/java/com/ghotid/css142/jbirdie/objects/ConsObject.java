package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.LispSource;

import java.util.List;

public class ConsObject extends LispObject {
    private final LispObject car;
    private final LispObject cdr;

    public ConsObject(LispSource source, LispObject car, LispObject cdr) {
        super(source);
        this.car = car;
        this.cdr = cdr;
    }

    public static LispObject fromList(LispSource source,
                                      List<?extends LispObject> list) {
        if (list.size() == 0)
            return NilObject.getNil();
        else
            return new ConsObject(
                    source,
                    list.get(0),
                    fromList(source, list.subList(1, list.size()))
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
        FuncObject func = context.evaluate(car).castTo(FuncObject.class);

        return func.call(context, cdr);
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
