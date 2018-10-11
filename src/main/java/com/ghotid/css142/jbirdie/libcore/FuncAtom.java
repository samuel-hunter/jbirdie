package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Second function required to implement a minimum Lisp.
 * <p>
 * "atom" accepts a value and returns the symbol "t" if it's atomic (i.e. it
 * can't be broken down by car or cdr); else, it returns nil.
 */
public class FuncAtom implements FuncObject {
    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        int size = new ConsList(args).size();
        if (size != 1)
            throw new ArgumentNumberException(size, "1");

        LispObject obj = args.getCar().evaluate(environment);
        if (obj instanceof ConsObject || obj instanceof NilObject)
            return NilObject.getNil();
        else
            return environment.getVariable("t");
    }
}
