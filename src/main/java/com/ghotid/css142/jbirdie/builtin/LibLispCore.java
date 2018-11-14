package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Collection of builtin functions often noted to be the foundation of lisp.
 */
public final class LibLispCore {
    private LibLispCore() {}

    @BuiltinFunc(name="quote", evalArgs = false, evalResult = false,
            doc="Return the first argument as a literal, i.e. without " +
                    "evaluation.")
    public static LispObject quote(InterpreterContext context,
                                   LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar();
    }

    @BuiltinFunc(name="atom", evalArgs = true, evalResult = false,
            doc="Return t if the first argument is an atomic data type, nil " +
                    "otherwise.")
    public static LispObject atom(InterpreterContext context,
                                  LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        LispObject obj = args.getCar();
        return SymbolObject.fromBoolean(obj instanceof AtomObject);
    }

    @BuiltinFunc(name="eq", evalArgs = true, evalResult = false,
            doc="Return t if the first two arguments have the same effective " +
                    "value, nil otherwise.")
    public static LispObject eq(InterpreterContext context, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        return SymbolObject.fromBoolean(
                argList.get(0).equals(argList.get(1))
        );
    }

    @BuiltinFunc(name="cons", evalArgs = true, evalResult = false,
            doc="Return a CONS object with the first argument as the CAR and " +
                    "the second as its CDR.")
    public static LispObject cons(InterpreterContext context, LispObject args) {
        ConsList list = new ConsList(args);
        list.assertSizeEquals(2);

        return new ConsObject(
                args.getSource(),
                list.get(0), list.get(1));
    }

    /**
     * <p>
     * 'cond' accepts a list of two-element lists. Going through each pair, if it
     * evaluates the first element (the conditional) to be true, then it
     * evaluates and returns the second element.
     * <p>
     * If none of the pairs' conditionals are true, then it returns nil.
     */
    @BuiltinFunc(name="cond", evalArgs = false, evalResult = true)
    public static LispObject cond(InterpreterContext context, LispObject args) {
        for (LispObject obj : new ConsList(args)) {
            ConsList pair = new ConsList(obj);
            pair.assertSizeEquals(2);

            LispObject conditional =
                    context.evaluate(pair.get(0));
            if (conditional.isTruthy())
                return pair.get(1);
        }

        return NilObject.getNil();
    }

    @BuiltinFunc(name="car", evalArgs = true, evalResult = false,
            doc="Return the first object of a list.")
    public static LispObject car(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar().getCar();
    }

    @BuiltinFunc(name="cdr", evalArgs = true, evalResult = false,
    doc="Return all but the first object of a list.")
    public static LispObject cdr(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar().getCdr();
    }


}
