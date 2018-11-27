package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.exception.LispException;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of lisp functions concerning string manipulation.
 */
public final class LibString {
    private LibString() {}

    @BuiltinFunc(name="concat", evalArgs = true, evalResult = false,
            doc="Concatenate multiple values into one string.")
    public static LispObject concat(InterpreterContext context, LispObject args) {
        StringBuilder sb = new StringBuilder();

        for (LispObject obj : new ConsList(args))
            sb.append(obj);

        return new StringObject(
                args.getSource(),
                sb.toString());
    }

    @BuiltinFunc(name="to-string", evalArgs = true, evalResult = false,
            doc="Convert the first argument into a string.")
    public static LispObject call(InterpreterContext context, LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        return new StringObject(
                args.getSource(),
                args.getCar().toString());
    }

    @BuiltinFunc(name="to-codepoints", evalArgs=true, evalResult=false,
            doc="Convert string into a list of codepoints.")
    public static LispObject toCodepoints(InterpreterContext context,
                                          LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        List<LispObject> result = new ArrayList<>();
        String str = args.getCar().castTo(StringObject.class).toString();
        for (int i = 0; i < str.length(); i++) {
            result.add(new IntegerObject(args.getSource(), str.codePointAt(i)));
        }

        return ConsObject.fromList(args.getSource(), result);
    }

    @BuiltinFunc(name="from-codepoints", evalArgs=true, evalResult=false,
            doc="Convert a list of integer-codepoints to a string.")
    public static LispObject fromCodepoints(InterpreterContext context,
                                                LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        ConsList codepoints = new ConsList(args.getCar());
        StringBuilder sb = new StringBuilder();
        for (LispObject codepointObj : codepoints) {
            int codepoint =
                    codepointObj.castTo(NumberObject.class).getValue().intValue();

            if (Character.isBmpCodePoint(codepoint)) {
                // Single-char codepoint.
                sb.append((char) codepoint);
            } else if (Character.isValidCodePoint(codepoint)) {
                // Two-char codepoint.
                sb.append(Character.highSurrogate(codepoint));
                sb.append(Character.lowSurrogate(codepoint));
            } else {
                throw new LispException("Invalid codepoint '" + codepoint + "'.");
            }
        }

        return new StringObject(args.getSource(), sb.toString());
    }

    @BuiltinFunc(name="char-at", evalArgs=true, evalResult=false,
            doc="Return the codepoint of a char at an index.")
    public static LispObject charAt(InterpreterContext context,
                                    LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        int index =
                argList.get(0).castTo(NumberObject.class).getValue().intValue();
        String str = argList.get(1).castTo(StringObject.class).getValue();

        int codepoint;

        try {
            codepoint = str.codePointAt(index);
        } catch (IndexOutOfBoundsException e) {
            throw new LispException("Index out of bounds: " + e.getMessage());
        }

        return new IntegerObject(args.getSource(), codepoint);
    }
}
