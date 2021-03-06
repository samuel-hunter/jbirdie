package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Builtin function collection concerning the change of state.
 */
public final class LibState {
    private LibState() {}

    private static LispObject def(InterpreterContext context, LispObject args,
                            boolean isConstant) {
        ConsList argList = new ConsList(args);
        // Allow third argument for documentation.
        argList.assertSizeWithin(2, 3);

        // Get the documentation, or an empty string otherwise.
        String doc = "";
        if (argList.size() == 3)
            doc = argList.get(2).castTo(StringObject.class).getValue();

        SymbolObject symbol = argList.get(0).castTo(SymbolObject.class);
        context.getEnvironment().def(
                symbol.getValue(),
                context.evaluate(argList.get(1)),
                doc, isConstant);

        return symbol;
    }

    @BuiltinFunc(name="defconst", evalArgs = false, evalResult = false,
            doc="Define a non-changing variable.")
    public static LispObject defconst(InterpreterContext context,
                                      LispObject args) {
        return def(context, args, true);
    }

    @BuiltinFunc(name="defvar", evalArgs = false, evalResult = false,
            doc="Define a variable.")
    public static LispObject defvar(InterpreterContext context,
                                    LispObject args) {
        return def(context, args, false);
    }

    @BuiltinFunc(name="setq", evalArgs = false, evalResult = false,
            doc="Set the value of a variable.")
    public static LispObject setq(InterpreterContext context, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        SymbolObject symbol = args.getCar().castTo(SymbolObject.class);

        LispObject value =
                context.evaluate(argList.get(1));

        context.getEnvironment().set(
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

        SymbolObject symbol = argList.get(0).castTo(SymbolObject.class);

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

    @BuiltinFunc(name="defun", evalArgs = false, evalResult = false,
            doc = "Define a function.")
    public static LispObject defun(InterpreterContext context, LispObject args) {
        return deflambda(context.getEnvironment(), args, false);
    }

    @BuiltinFunc(name="defmacro", evalArgs = false, evalResult = false,
            doc = "Define a macro.")
    public static LispObject defmacro(InterpreterContext context,
                                      LispObject args) {
        return deflambda(context.getEnvironment(), args, true);
    }
}
