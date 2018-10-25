package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * While not considered part of the Big Seven, these lisp functions are still
 * extremely important for a usable lisp.
 */
public final class LibLispExtra {
    private LibLispExtra() {}

    @BuiltinFunc(name="lambda")
    public static LispObject lambda(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(args.getCar(), args.getCdr());
    }

    @BuiltinFunc(name="macro")
    public static LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(args.getCar(), args.getCdr(), true);
    }

    @BuiltinFunc(name="apply")
    public static LispObject apply(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        return new ConsObject(
                argList.get(0), argList.get(1).evaluate(environment)
        ).evaluate(environment);
    }

    @BuiltinFunc(name="progn",
            doc="Evaluate the list of expressions and return the value of the" +
                    " last expression.")
    public static LispObject progn(Environment environment, LispObject args) {
        return LispUtils.progn(environment, args);
    }
}
