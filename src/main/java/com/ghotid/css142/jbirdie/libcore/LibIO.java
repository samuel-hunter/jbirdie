package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.Scanner;

/**
 * Collection of builtin functions concerning I/O operations.
 */
public final class LibIO {
    private LibIO() {}

    @BuiltinFunc(name="read-line",
            doc="Read the next line from standard input, and return it.")
    public static LispObject readLine(Environment environment,
                                      LispObject args) {
        new ConsList(args).assertSizeEquals(0);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        // Do not close the scanner here; it will cause an IOException.
        return new StringObject(line);
    }

    @BuiltinFunc(name="read-number",
            doc="Read the next number from standard input, and return it.")
    public static LispObject readNumber(Environment environment,
                                      LispObject args) {
        new ConsList(args).assertSizeEquals(0);

        Scanner scanner = new Scanner(System.in);
        double value = scanner.nextDouble();

        // Do not close the scanner here; it will cause an IOException.
        return new NumberObject(value);
    }

    @BuiltinFunc(name="print",
            doc="Print all arguments to standard output.")
    public static LispObject print(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        StringBuilder sb = new StringBuilder();

        for (LispObject arg : argList)
            sb.append(arg.evaluate(environment));

        String result = sb.toString();
        System.out.print(result);

        // Return the stringified result.
        return new StringObject(result);
    }

    @BuiltinFunc(name="println",
            doc="Print all arguments to standard output, followed by a newline.")
    public static LispObject println(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        StringBuilder sb = new StringBuilder();

        for (LispObject arg : argList)
            sb.append(arg.evaluate(environment));

        String result = sb.toString();
        System.out.println(result);

        // Return the stringified result.
        return new StringObject(result);
    }
}
