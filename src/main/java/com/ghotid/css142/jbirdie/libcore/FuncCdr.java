package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Seventh and final function required to implement a minimal lisp.
 */
public class FuncCdr implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        int size = new ConsList(args).size();
        if (size != 1)
            throw new ArgumentNumberException(size, "1");

        return args.getCar().evaluate(environment).getCdr();
    }
}
