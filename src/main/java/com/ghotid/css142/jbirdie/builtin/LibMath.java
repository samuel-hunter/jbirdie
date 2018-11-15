package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Collection of builtin functions concerning mathematical operations.
 */
public final class LibMath {
    private LibMath() {}

    @BuiltinFunc(name="+", evalArgs = true, evalResult = false,
            doc="Return the sum of its arguments. With no args, return 0.")
    public static LispObject add(InterpreterContext context,
                                 LispObject args) {
        Double result = 0.0;

        for (LispObject obj : new ConsList(args)) {
            result += obj.castTo(NumberObject.class).getValue();
        }

        return new NumberObject(args.getSource(), result);
    }

    @BuiltinFunc(name="-", evalArgs = true, evalResult = false,
            doc="Subtract the second and all subsequent arguments from the " +
                    "first; or, with one argument, negate it.")
    public static LispObject subtract(InterpreterContext context,
                                      LispObject args) {
        int size = new ConsList(args).assertSizeAtLeast(1);

        Double value = args.getCar().castTo(NumberObject.class).getValue();

        if (size == 1)
            return new NumberObject(args.getSource(), -value);

        for (LispObject obj : new ConsList(args.getCdr())) {
            value -= obj.castTo(NumberObject.class).getValue();
        }

        return new NumberObject(args.getSource(), value);
    }

    @BuiltinFunc(name="*", evalArgs = true, evalResult = false,
            doc="Return the product of its arguments. With no args, return 1.")
    public static LispObject multiply(InterpreterContext context,
                                      LispObject args) {
        Double result = 1.0;

        for (LispObject obj : new ConsList(args)) {
            result *= obj.castTo(NumberObject.class).getValue();
        }

        return new NumberObject(args.getSource(), result);
    }

    @BuiltinFunc(name="/", evalArgs = true, evalResult = false,
            doc="Divide the first argument by each of the following " +
                    "arguments, in turn. With one argument, return its " +
                    "reciprocal.")
    public static LispObject divide(InterpreterContext context, LispObject args) {
        int size = new ConsList(args).assertSizeAtLeast(1);

        Double quotient = args.getCar().castTo(NumberObject.class).getValue();

        if (size == 1)
            return new NumberObject(args.getSource(), 1 / quotient);

        for (LispObject obj : new ConsList(args.getCdr())) {
            quotient /= obj.castTo(NumberObject.class).getValue();
        }

        return new NumberObject(args.getSource(), quotient);
    }

    @BuiltinFunc(name="<", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly " +
                    "increasing order, nil otherwise.")
    public static LispObject lessThan(InterpreterContext context,
                                      LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        // Get the value of the first number to compare with.
        double smallerNum = args.getCar().castTo(NumberObject.class).getValue();

        for (LispObject obj : new ConsList(args.getCdr())) {
            double largerNum = obj.castTo(NumberObject.class).getValue();

            // If the current number is greater than the previous, the
            // condition is not satisfied.
            if (!(smallerNum < largerNum))
                return NilObject.getNil();

            smallerNum = largerNum;
        }

        return SymbolObject.getT();
    }

    @BuiltinFunc(name="<=", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly " +
                    "non-decreasing order, nil otherwise.")
    public static LispObject lessThanEqual(InterpreterContext context,
                                      LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        // Get the value of the first number to compare with.
        double smallerNum = args.getCar().castTo(NumberObject.class).getValue();

        for (LispObject obj : new ConsList(args.getCdr())) {
            double largerNum = obj.castTo(NumberObject.class).getValue();

            // If the current number is greater than the previous, the
            // condition is not satisfied.
            if (!(smallerNum <= largerNum))
                return NilObject.getNil();

            smallerNum = largerNum;
        }

        return SymbolObject.getT();
    }

    @BuiltinFunc(name=">=", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly " +
                    "non-increasing order, nil otherwise.")
    public static LispObject isGreaterEqual(InterpreterContext context,
                                      LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        double largerNum = args.getCar().castTo(NumberObject.class).getValue();

        for (LispObject obj : new ConsList(args.getCdr())) {
            double smallerNum = obj.castTo(NumberObject.class).getValue();

            // Return false immediately if one of the expressions is not valid.
            if (!(largerNum >= smallerNum))
                return NilObject.getNil();

            largerNum = smallerNum;
        }

        return SymbolObject.getT();
    }

    @BuiltinFunc(name=">", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly increasing " +
                    "order, nil otherwise.")
    public static LispObject isGreater(InterpreterContext context,
                                      LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        double largerNum = args.getCar().castTo(NumberObject.class).getValue();

        for (LispObject obj : new ConsList(args.getCdr())) {
            double smallerNum = obj.castTo(NumberObject.class).getValue();

            // Return false immediately if one of the expressions is not valid.
            if (!(largerNum > smallerNum))
                return NilObject.getNil();

            largerNum = smallerNum;
        }

        return SymbolObject.getT();
    }

    @BuiltinFunc(name="%", evalArgs = true, evalResult = false,
            doc="Return X % Y.")
    public static LispObject mod(InterpreterContext context, LispObject args)
    {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        double dividend = argList.get(0).castTo(NumberObject.class).getValue();

        double divisor = argList.get(1).castTo(NumberObject.class).getValue();

        return new NumberObject(args.getSource(),dividend % divisor);
    }
}
