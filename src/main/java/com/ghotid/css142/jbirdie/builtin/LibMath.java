package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.function.Predicate;

/**
 * Collection of builtin functions concerning mathematical operations.
 */
public final class LibMath {
    private LibMath() {}

    @BuiltinFunc(name="+", evalArgs = true, evalResult = false,
            doc="Return the sum of its arguments. With no args, return 0.")
    public static LispObject add(InterpreterContext context,
                                 LispObject args) {
        NumberObject result = new IntegerObject(args.getSource(), 0);

        for (LispObject obj : new ConsList(args)) {
            result = result.add(obj.castTo(NumberObject.class));
        }

        return result;
    }

    @BuiltinFunc(name="-", evalArgs = true, evalResult = false,
            doc="Subtract the second and all subsequent arguments from the " +
                    "first; or, with one argument, negate it.")
    public static LispObject subtract(InterpreterContext context,
                                      LispObject args) {
        int size = new ConsList(args).assertSizeAtLeast(1);

        NumberObject result = args.getCar().castTo(NumberObject.class);

        if (size == 1)
            return result.neg();

        for (LispObject obj : new ConsList(args.getCdr())) {
            result = result.sub(obj.castTo(NumberObject.class));
        }

        return result;
    }

    @BuiltinFunc(name="*", evalArgs = true, evalResult = false,
            doc="Return the product of its arguments. With no args, return 1.")
    public static LispObject multiply(InterpreterContext context,
                                      LispObject args) {
        NumberObject result = new IntegerObject(args.getSource(), 1);

        for (LispObject obj : new ConsList(args)) {
            result = result.mul(obj.castTo(NumberObject.class));
        }

        return result;
    }

    @BuiltinFunc(name="/", evalArgs = true, evalResult = false,
            doc="Divide the first argument by each of the following " +
                    "arguments, in turn. With one argument, return its " +
                    "reciprocal.")
    public static LispObject divide(InterpreterContext context, LispObject args) {
        int size = new ConsList(args).assertSizeAtLeast(1);

        NumberObject result = args.getCar().castTo(NumberObject.class);

        if (size == 1)
            return new IntegerObject(args.getSource(), 1).div(result);

        for (LispObject obj : new ConsList(args.getCdr())) {
            result = result.div(obj.castTo(NumberObject.class));
        }

        return result;
    }

    private static LispObject cmp(LispObject args,
                                  Predicate<Integer> trueCase) {
        new ConsList(args).assertSizeAtLeast(1);

        NumberObject firstNum = args.getCar().castTo(NumberObject.class);
        for (LispObject obj : new ConsList(args.getCdr())) {
            NumberObject secondNum = obj.castTo(NumberObject.class);

            if (!trueCase.test(firstNum.cmp(secondNum)))
                return NilObject.getNil();

            firstNum = secondNum;
        }

        return SymbolObject.getT();
    }

    @BuiltinFunc(name="<", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly " +
                    "increasing order, nil otherwise.")
    public static LispObject lessThan(InterpreterContext context,
                                      LispObject args) {
        return cmp(args, (cmp) -> cmp < 0);
    }

    @BuiltinFunc(name="<=", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly " +
                    "non-decreasing order, nil otherwise.")
    public static LispObject lessThanEqual(InterpreterContext context,
                                      LispObject args) {
        return cmp(args, (cmp) -> cmp <= 0);
    }

    @BuiltinFunc(name=">=", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly " +
                    "non-increasing order, nil otherwise.")
    public static LispObject isGreaterEqual(InterpreterContext context,
                                      LispObject args) {
        return cmp(args, (cmp) -> cmp >= 0);
    }

    @BuiltinFunc(name=">", evalArgs = true, evalResult = false,
            doc="Return t if all of its arguments are in strictly increasing " +
                    "order, nil otherwise.")
    public static LispObject isGreater(InterpreterContext context,
                                      LispObject args) {
        return cmp(args, (cmp) -> cmp > 0);
    }

    @BuiltinFunc(name="%", evalArgs = true, evalResult = false,
            doc="Return X % Y.")
    public static LispObject mod(InterpreterContext context, LispObject args)
    {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        NumberObject dividend = argList.get(0).castTo(NumberObject.class);

        NumberObject divisor = argList.get(1).castTo(NumberObject.class);

        return dividend.mod(divisor);
    }
}
