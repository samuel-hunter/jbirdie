package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * While not considered part of the Big Seven, these lisp functions are still
 * extremely important for a usable lisp.
 */
public final class LibLispExtra {
    private LibLispExtra() {}

    @BuiltinFunc(name="lambda", evalArgs = false, evalResult = false)
    public static LispObject lambda(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(context.getEnvironment(), args, false);
    }

    @BuiltinFunc(name="macro", evalArgs = false, evalResult = false)
    public static LispObject macro(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(context.getEnvironment(), args, true);
    }

    @BuiltinFunc(name="apply", evalArgs = true, evalResult = true)
    public static LispObject apply(InterpreterContext context, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        return new ConsObject(
                argList.get(0), argList.get(1)
        );
    }

    @BuiltinFunc(name="let", evalArgs = false, evalResult = false,
            doc = "Bind variables to its values and run the body.")
    public static LispObject let(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);
        ConsList varBindings = new ConsList(args.getCar());
        LispObject body = args.getCdr();

        Environment bodyEnv = context.getEnvironment().pushStack();
        for (LispObject binding : varBindings) {
            ConsList bindList = new ConsList(binding);
            bindList.assertSizeEquals(2);

            String symbol = LispObject.cast(
                    SymbolObject.class,
                    bindList.get(0)
            ).getValue();
            bodyEnv.def(symbol, context.evaluate(bindList.get(1)),
                    false);
        }

        return LispUtils.progn(context.withEnvironment(bodyEnv), body);
    }

    @BuiltinFunc(name="progn", evalArgs = false, evalResult = false,
            doc="Evaluate the list of expressions and return the value of the" +
                    " last expression.")
    public static LispObject progn(InterpreterContext context, LispObject args) {
        return LispUtils.progn(context, args);
    }
}
