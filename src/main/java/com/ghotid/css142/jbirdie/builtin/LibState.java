package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Builtin function collection concerning the change of state.
 */
public final class LibState {
    private LibState() {}

    private static LispObject def(Environment environment, LispObject args,
                            boolean isConstant) {
        ConsList argList = new ConsList(args);
        // Allow third argument for documentation.
        argList.assertSizeWithin(2, 3);

        // Get the documentation, or an empty string otherwise.
        String doc = "";
        if (argList.size() == 3)
            doc = LispObject.cast(
                    StringObject.class,
                    argList.get(2)
            ).getValue();

        SymbolObject symbol =
                LispObject.cast(SymbolObject.class, argList.get(0));
        environment.def(
                symbol.getValue(),
                argList.get(1).evaluate(environment),
                doc, isConstant);

        return symbol;
    }

    @BuiltinFunc(name="defconst", evalArgs = false,
            doc="Define a non-changing variable.")
    public static LispObject defconst(Environment environment,
                                      LispObject args) {
        return def(environment, args, true);
    }

    @BuiltinFunc(name="defvar", evalArgs = false,
            doc="Define a variable.")
    public static LispObject defvar(Environment environment, LispObject args) {
        return def(environment, args, false);
    }

    @BuiltinFunc(name="setq", evalArgs = false,
            doc="Set the value of a variable.")
    public static LispObject setq(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        SymbolObject symbol = LispObject.cast(
                SymbolObject.class, args.getCar());

        LispObject value = argList.get(1).evaluate(environment);

        environment.set(
                symbol.getValue(),
                value
        );

        return symbol;
    }

    private static LispObject deflambda(Environment environment,
                                        LispObject args, boolean isMacro) {
        ConsList argList = new ConsList(args);
        // arg 0 = name; arg 1 = paramlist; rest is body.
        argList.assertSizeAtLeast(2);

        SymbolObject symbol = LispObject.cast(
                SymbolObject.class,
                argList.get(0)
        );

        LispObject lambdaArgs = args.getCdr();

        String doc = "";

        // The third argument has the option of being a stringdoc.
        LispObject docObj = argList.get(2);
        if (docObj instanceof StringObject)
            doc = ((StringObject) docObj).getValue();

        environment.def(
                symbol.getValue(),
                new LambdaObject(environment, lambdaArgs, isMacro),
                doc,true
        );

        return symbol;
    }

    @BuiltinFunc(name="defun", evalArgs = false,
            doc = "Define a function.")
    public static LispObject defun(Environment environment, LispObject args) {
        return deflambda(environment, args, false);
    }

    @BuiltinFunc(name="defmacro", evalArgs = false,
            doc = "Define a macro.")
    public static LispObject defmacro(Environment environment,
                                      LispObject args) {
        return deflambda(environment, args, true);
    }
}
