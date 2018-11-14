package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.LispSource;
import com.ghotid.css142.jbirdie.exception.InvalidTypeException;

public abstract class LispObject {
    private final LispSource source;

    public LispObject(LispSource source) {
        this.source = source;
    }

    public LispObject getCar() {
        throw new InvalidTypeException("List", this);
    }

    public LispObject getCdr() {
        throw new InvalidTypeException("List", this);
    }

    public LispResult evaluate(InterpreterContext context) {
        return new LispResult(this, true);
    }

    public boolean isTruthy() {
        return true;
    }

    public String inspect() {
        return toString();
    }

    public static <T extends LispObject> T cast(Class<? extends T> lispClass,
                                                LispObject obj) {
        if (lispClass.isInstance(obj))
            return lispClass.cast(obj);

        throw new InvalidTypeException(lispClass, obj);
    }

    public LispSource getSource() {
        return source;
    }
}
