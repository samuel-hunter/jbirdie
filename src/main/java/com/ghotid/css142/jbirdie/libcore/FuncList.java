package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

/**
 * Return a cons list of all functions.
 */
public class FuncList implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        if (args instanceof NilObject) {
            return args;
        } else {
            return new ConsObject(
                    args.getCar().evaluate(environment),
                    call(environment, args.getCdr())
            );
        }
    }
}
