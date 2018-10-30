package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Collection of lisp functions concerning string manipulation.
 */
public final class LibString {
    private LibString() {}

    @BuiltinFunc(name="concat", evalArgs = true,
            doc="Concatenate multiple values into one string.")
    public static LispObject concat(Environment environment, LispObject args) {
        StringBuilder sb = new StringBuilder();

        for (LispObject obj : new ConsList(args))
            sb.append(obj);

        return new StringObject(sb.toString());
    }

    @BuiltinFunc(name="to-string", evalArgs = true,
            doc="Convert the first argument into a string.")
    public static LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        return new StringObject(
                args.getCar().toString());
    }
}
