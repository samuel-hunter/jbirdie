package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.StringObject;

import java.util.Scanner;

/**
 * Read a line from standard input and return a string.
 */
public class FuncReadLine extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(0);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        // Do not close the scanner here; it will cause an IOException.
        return new StringObject(line);
    }
}
