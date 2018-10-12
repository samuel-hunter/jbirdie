package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Second function required to implement a minimum Lisp.
 * <p>
 * "atom" accepts a value and returns the symbol "t" if it's atomic (i.e. it
 * can't be broken down by car or cdr); else, it returns nil.
 */
public class FuncAtom implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        LispObject obj = args.getCar().evaluate(environment);
        if (obj instanceof ConsObject || obj instanceof NilObject)
            return NilObject.getNil();
        else
            return environment.get("t");
    }
}
