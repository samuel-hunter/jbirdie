package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispUtils;
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

    @BuiltinFunc(name="list",
    doc="Build a list from its arguments.")
    public static LispObject list(Environment environment, LispObject args) {
        return LispUtils.evalList(environment, new ConsList(args));
    }

    @BuiltinFunc(name="append",
    doc="Combine multiple lists and return the result.")
    public static LispObject append(Environment environment, LispObject args) {
        ConsList argList = new ConsList(
                LispUtils.evalList(environment, new ConsList(args))
        );
        ArrayList<LispObject> result = new ArrayList<>();

        for (LispObject arg : argList)
            result.addAll(new ConsList(arg));

        return ConsObject.fromList(result);
    }
}
