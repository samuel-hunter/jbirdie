package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Sixth function required to implement a minimal lisp.
 */
public class FuncCar implements FuncObject {
    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        int size = new ConsList(args).size();
        if (size != 1)
            throw new ArgumentNumberException(size, "1");

        return args.getCar().evaluate(environment).getCar();
    }
}
