package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Collection of builtin functions often noted to be the foundation of lisp.
 */
public final class LibLispCore {
    private LibLispCore() {}

    @BuiltinFunc(name="quote",
            doc="Return the first argument as a literal, i.e. without " +
                    "evaluation.")
    public static LispObject quote(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar();
    }

    @BuiltinFunc(name="atom",
            doc="Return t if the first argument is an atomic data type, nil " +
                    "otherwise.")
    public static LispObject atom(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        LispObject obj = args.getCar().evaluate(environment);
        return SymbolObject.fromBoolean(obj instanceof AtomObject);
    }

    @BuiltinFunc(name="eq",
            doc="Return t if the first two arguments have the same effective " +
                    "value, nil otherwise.")
    public static LispObject eq(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        return SymbolObject.fromBoolean(
                argList.get(0).evaluate(environment).equals(
                        argList.get(1).evaluate(environment)
                )
        );
    }

    @BuiltinFunc(name="cons",
            doc="Return a CONS object with the first argument as the CAR and " +
                    "the second as its CDR.")
    public static LispObject cons(Environment environment, LispObject args) {
        ConsList list = new ConsList(args);
        list.assertSizeEquals(2);

        return new ConsObject(
                list.get(0).evaluate(environment),
                list.get(1).evaluate(environment));
    }

    /**
     * <p>
     * 'cond' accepts a list of two-element lists. Going through each pair, if it
     * evaluates the first element (the conditional) to be true, then it
     * evaluates and returns the second element.
     * <p>
     * If none of the pairs' conditionals are true, then it returns nil.
     */
    @BuiltinFunc(name="cond")
    public static LispObject cond(Environment environment, LispObject args) {
        for (LispObject obj : new ConsList(args)) {
            ConsList pair = new ConsList(obj);
            pair.assertSizeEquals(2);

            LispObject conditional = pair.get(0).evaluate(environment);
            if (conditional.isTruthy())
                return pair.get(1).evaluate(environment);
        }

        return NilObject.getNil();
    }

    @BuiltinFunc(name="car",
            doc="Return the first object of a list.")
    public static LispObject car(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar().evaluate(environment).getCar();
    }

    @BuiltinFunc(name="cdr",
    doc="Return all but the first object of a list.")
    public static LispObject cdr(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar().evaluate(environment).getCdr();
    }


}
