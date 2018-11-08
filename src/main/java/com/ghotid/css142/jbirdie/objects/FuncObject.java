package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;

public abstract class FuncObject extends AtomObject {

    public abstract LispResult call(InterpreterContext context,
                                    LispObject args);

    @Override
    public Class getValue() {
        return getClass();
    }
}
