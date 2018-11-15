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

    public <T extends LispObject> T castTo(Class<T> lispClass) {
        if (lispClass.isInstance(this)) {
            return lispClass.cast(this);
        } else {
            throw new InvalidTypeException(lispClass, this);
        }
    }

    public LispSource getSource() {
        return source;
    }
}
