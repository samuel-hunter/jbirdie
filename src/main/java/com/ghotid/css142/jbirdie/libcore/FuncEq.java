package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Third function required to implement a minimal lisp.
 * <p>
 * "eq" accepts two values. It returns the symbol "t" if they both point to the
 * same object; else, it returns nil.
 */
public class FuncEq implements FuncObject {
    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        int size = argList.size();

        if (size != 2)
            throw new ArgumentNumberException(size, "2");

        if (argList.get(0).evaluate(environment).equalsHard(
                argList.get(1).evaluate(environment))) {

            return environment.getVariable("t");
        } else {
            return NilObject.getNil();
        }
    }
}