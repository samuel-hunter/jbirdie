package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Builtin functions concerning the control flow of the program.
 */
public final class LibControl {
    private LibControl() {}

    @BuiltinFunc(name = "while", evalArgs = false, evalResult = false,
            doc = "Repeat executing the body so long as the conditional is " +
                    "true.")
    public static LispObject while_(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        LispObject result = NilObject.getNil();
        LispObject conditional = args.getCar();
        LispObject body = args.getCdr();

        while (context.evaluate(conditional).isTruthy()) {
            // Give the construct its own scope.
            result = LispUtils.progn(context.pushEnvironment(), body);
        }

        return result;
    }

    @BuiltinFunc(name = "foreach", evalArgs = false, evalResult = false,
            doc="Iterate over the body with each variable")
    public static LispObject forEach(InterpreterContext context, LispObject args) {
        ConsList header = new ConsList(args.getCar());
        header.assertSizeEquals(2);

        String elementName = LispObject.cast(
                SymbolObject.class,
                header.get(0)
        ).getValue();

        ConsList list = new ConsList(context.evaluate(header.get(1)));
        LispObject body = args.getCdr();
        LispObject result = NilObject.getNil();

        for (LispObject element : list) {
            Environment loopEnvironment = context.getEnvironment().pushStack();
            loopEnvironment.def(elementName, element, false);
            result = LispUtils.progn(
                    context.withEnvironment(loopEnvironment), body);
        }

        return result;
    }
}
