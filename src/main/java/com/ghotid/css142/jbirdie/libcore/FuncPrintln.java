package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.StringObject;

/**
 * Print the string without a line.
 */
public class FuncPrintln implements FuncObject {

    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        StringObject string = LispObject.cast(
                StringObject.class,
                args.getCar()
        );

        System.out.println(string.getValue());
        return string;
    }
}
