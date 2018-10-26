package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * While not considered part of the Big Seven, these lisp functions are still
 * extremely important for a usable lisp.
 */
public final class LibLispExtra {
    private LibLispExtra() {}

    @BuiltinFunc(name="lambda", evalArgs = false)
    public static LispObject lambda(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(environment, args, false);
    }

    @BuiltinFunc(name="macro", evalArgs = false)
    public static LispObject macro(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(environment, args, true);
    }

    @BuiltinFunc(name="apply", evalArgs = true)
    public static LispObject apply(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        return new ConsObject(
                argList.get(0), argList.get(1)
        ).evaluate(environment);
    }

    @BuiltinFunc(name="progn", evalArgs = false,
            doc="Evaluate the list of expressions and return the value of the" +
                    " last expression.")
    public static LispObject progn(Environment environment, LispObject args) {
        return LispUtils.progn(environment, args);
    }
}
