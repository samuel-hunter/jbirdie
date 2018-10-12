package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.App;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Set the debug state of the interpreter.
 *
 * @see App#setDebug(Boolean)
 */
public class FuncSetdebug implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        LispObject willDebug = args.getCar();
        App.setDebug(willDebug.isTruthy());

        return willDebug;
    }
}
