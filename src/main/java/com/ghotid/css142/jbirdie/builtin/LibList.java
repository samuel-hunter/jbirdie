package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.ArrayList;

/**
 * Builtin lisp functions concerning lists.
 */
public final class LibList {
    private LibList() {}

    @BuiltinFunc(name="list", evalArgs = true, evalResult = false,
    doc="Build a list from its arguments.")
    public static LispObject list(InterpreterContext context, LispObject args) {
        return args;
    }

    @BuiltinFunc(name="append", evalArgs = true, evalResult = false,
    doc="Combine multiple lists and return the result.")
    public static LispObject append(InterpreterContext context,
                                    LispObject args) {
        ConsList argList = new ConsList(args);
        ArrayList<LispObject> result = new ArrayList<>();

        for (LispObject arg : argList)
            result.addAll(new ConsList(arg));

        return ConsObject.fromList(args.getSource(), result);
    }
}
