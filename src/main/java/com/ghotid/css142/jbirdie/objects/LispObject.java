package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.InvalidTypeException;

public interface LispObject {
    default LispObject getCar() {
        throw new InvalidTypeException("List", this);
    }

    default LispObject getCdr() {
        throw new InvalidTypeException("List", this);
    }

    default LispResult evaluate(InterpreterContext context) {
        return new LispResult(this, true);
    }

    default boolean isTruthy() {
        return true;
    }

    default String inspect() {
        return toString();
    }

    static <T extends LispObject> T cast(Class<? extends T> lispClass,
                                         LispObject obj) {
        if (lispClass.isInstance(obj))
            return lispClass.cast(obj);

        throw new InvalidTypeException(lispClass, obj);
    }
}
