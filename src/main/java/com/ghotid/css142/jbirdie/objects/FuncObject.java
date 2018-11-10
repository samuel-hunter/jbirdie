package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.LispUtils;

public abstract class FuncObject extends AtomObject {

    final LispResult call(InterpreterContext context,
                                    LispObject args) {
        if (isMacro())
            return apply(context, args);
        else
            return apply(context,
                    LispUtils.evalList(context, new ConsList(args)));
    }

    public abstract LispResult apply(InterpreterContext context,
                                     LispObject args);

    public abstract boolean isMacro();

    @Override
    public Class getValue() {
        return getClass();
    }
}
