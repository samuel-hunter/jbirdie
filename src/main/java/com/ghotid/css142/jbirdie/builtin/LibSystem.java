package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.JBirdie;
import com.ghotid.css142.jbirdie.exception.LispExitException;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Collection of builtin lisp functions concerning the interpreter and general
 * system.
 */
public final class LibSystem {
    private LibSystem() {}

    @BuiltinFunc(name="debug", evalArgs = true, evalResult = false,
            doc="With no arguments, return whether debug mode is on. With one" +
                    " argument, set the interpreter's debug mode.")
    public static LispObject debug(InterpreterContext context, LispObject args) {
        int size = new ConsList(args).assertSizeWithin(0, 1);

        if (size == 0) {
            return SymbolObject.fromBoolean(JBirdie.isDebugging());
        } else {
            LispObject willDebug = args.getCar();
            JBirdie.setDebugging(willDebug.isTruthy());
            return willDebug;
        }
    }

    @BuiltinFunc(name="exit", evalArgs = true, evalResult = false,
            doc="Exit the program. With an argument, set the status code.")
    public static LispObject exit(InterpreterContext context, LispObject args) {
        int size = new ConsList(args).assertSizeWithin(0, 1);

        if (size == 0) {
            throw new LispExitException(0);
        } else {
            NumberObject exitCode = LispObject.cast(
                    NumberObject.class,
                    args.getCar());

            throw new LispExitException(exitCode.getValue().intValue());
        }
    }
}
