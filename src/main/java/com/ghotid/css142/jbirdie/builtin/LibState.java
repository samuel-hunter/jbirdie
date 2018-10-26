package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Builtin function collection concerning the change of state.
 */
public final class LibState {
    private LibState() {}

    @BuiltinFunc(name="defconst", evalArgs = false,
            doc="Define a non-changing variable.")
    public static LispObject defconst(Environment environment,
                                      LispObject args) {
        ConsList argList = new ConsList(args);
        // Allow third argument, to implement documentation later.
        argList.assertSizeWithin(2, 3);

        SymbolObject symbol =
                LispObject.cast(SymbolObject.class, argList.get(0));
        environment.def(symbol.getValue(),
                argList.get(1).evaluate(environment), true);

        return symbol;
    }

    @BuiltinFunc(name="defvar", evalArgs = false,
            doc="Define a variable.")
    public static LispObject defvar(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        // Allow third argument, to implement documentation later.
        argList.assertSizeWithin(2, 3);

        SymbolObject symbol =
                LispObject.cast(SymbolObject.class, argList.get(0));
        environment.def(symbol.getValue(),
                argList.get(1).evaluate(environment), false);

        return symbol;
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
}
