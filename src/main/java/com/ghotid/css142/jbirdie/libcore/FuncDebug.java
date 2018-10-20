package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.App;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * With no arguments, return the debug state of the interpreter. With a
 * value, set the debug state of the interpreter.
 *
 * @see App#setDebug(Boolean)
 */
public class FuncDebug extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        int size = new ConsList(args).assertSizeWithin(0, 1);

        if (size == 0) {
            return SymbolObject.fromBoolean(App.getDebug());
        } else {
            LispObject willDebug = args.getCar().evaluate(environment);
            App.setDebug(willDebug.isTruthy());
            return willDebug;
        }
    }
}
