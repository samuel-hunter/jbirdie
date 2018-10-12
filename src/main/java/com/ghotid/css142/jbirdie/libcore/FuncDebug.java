package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.App;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

/**
 * With no arguments, return the debug state of the interpreter. With a
 * value, set the debug state of the interpreter.
 *
 * @see App#setDebug(Boolean)
 */
public class FuncDebug implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        int size = new ConsList(args).assertSizeWithin(0, 1);

        if (size == 0) {
            if (App.getDebug())
                return environment.get("t");
            else
                return NilObject.getNil();
        } else {
            LispObject willDebug = args.getCar().evaluate(environment);
            App.setDebug(willDebug.isTruthy());
            return willDebug;
        }
    }
}
