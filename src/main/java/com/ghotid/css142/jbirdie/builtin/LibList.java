package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.ArrayList;

/**
 * Builtin lisp functions concerning lists.
 */
public final class LibList {
    private LibList() {}

    @BuiltinFunc(name="list", evalArgs = true,
    doc="Build a list from its arguments.")
    public static LispObject list(Environment environment, LispObject args) {
        return args;
    }

    @BuiltinFunc(name="append", evalArgs = true,
    doc="Combine multiple lists and return the result.")
    public static LispObject append(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        ArrayList<LispObject> result = new ArrayList<>();

        for (LispObject arg : argList)
            result.addAll(new ConsList(arg));

        return ConsObject.fromList(result);
    }
}
