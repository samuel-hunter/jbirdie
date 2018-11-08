package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.Scanner;

/**
 * Collection of builtin functions concerning I/O operations.
 */
public final class LibIO {
    private LibIO() {}

    @BuiltinFunc(name="read-line", evalArgs = true, evalResult = false,
            doc="Read the next line from standard input, and return it.")
    public static LispObject readLine(InterpreterContext context,
                                      LispObject args) {
        new ConsList(args).assertSizeEquals(0);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        // Do not close the scanner here; it will cause an IOException.
        return new StringObject(line);
    }

    @BuiltinFunc(name="read-number", evalArgs = true, evalResult = false,
            doc="Read the next number from standard input, and return it.")
    public static LispObject readNumber(InterpreterContext context,
                                      LispObject args) {
        new ConsList(args).assertSizeEquals(0);

        Scanner scanner = new Scanner(System.in);
        double value = scanner.nextDouble();

        // Do not close the scanner here; it will cause an IOException.
        return new NumberObject(value);
    }

    @BuiltinFunc(name="print", evalArgs = true, evalResult = false,
            doc="Print all arguments to standard output.")
    public static LispObject print(InterpreterContext context, LispObject args) {
        ConsList argList = new ConsList(args);
        StringBuilder sb = new StringBuilder();

        for (LispObject arg : argList)
            sb.append(arg);

        String result = sb.toString();
        System.out.print(result);

        // Return the stringified result.
        return new StringObject(result);
    }

    @BuiltinFunc(name="println", evalArgs = true, evalResult = false,
            doc="Print all arguments to standard output, followed by a newline.")
    public static LispObject println(InterpreterContext context, LispObject args) {
        ConsList argList = new ConsList(args);
        StringBuilder sb = new StringBuilder();

        for (LispObject arg : argList)
            sb.append(arg);

        String result = sb.toString();
        System.out.println(result);

        // Return the stringified result.
        return new StringObject(result);
    }
}
