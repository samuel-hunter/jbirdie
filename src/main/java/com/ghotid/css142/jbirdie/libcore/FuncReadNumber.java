package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NumberObject;

import java.util.Scanner;

public class FuncReadNumber extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(0);

        Scanner scanner = new Scanner(System.in);
        double value = scanner.nextDouble();

        // Do not close the scanner here; it will cause an IOException.
        return new NumberObject(value);
    }
}
